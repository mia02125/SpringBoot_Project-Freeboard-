# SpringBoot(게시판만들기)
<a href = "https://github.com/mia02125/-SpringBoot_Project-Freeboard-2">MyBatis로 만든 게시판 보러가기</a>
## * SpringBoot 초기 설정 
##### ㆍ Lombok(getter & setter 자동 설정) 
##### ㆍ Spring Data JPA 
##### ㆍ Spring Web
##### ㆍ Thymeleaf

## * MVC방식으로 구현
##### ㆍ Route -> Controller -> View로 연결되는 과정 학습
##### ㆍ Route에서 app/HTTP/Controller경로와 사용 function을 지정
##### ㆍ app/HTTP/Controller에서 Function를 만들어 resources/view/보여줄 파일로 전송
##### ㆍ view 폴더에 파일에서 최종적으로 출력

<br><br><br>

## 01.06
## 가입페이지 로그입 페이지 링크 달기 
![create boot](https://raw.githubusercontent.com/mia02125/SpringBoot_Project-Freeboard-/master/pic/pic.PNG)
## 로그인창 구현 
![create boot](https://raw.githubusercontent.com/mia02125/SpringBoot_Project-Freeboard-/master/pic/pic_1.PNG)

<br><br><br>

### =========================================================

## 01.07
## 비밀번호 암호화(SHA-256 알고리즘) 
![create boot](https://raw.githubusercontent.com/mia02125/SpringBoot_Project-Freeboard-/master/pic/pic2.PNG)

#### 패스워드 암호화(SHA-256알고리즘)
```java
@Service
public class UserPasswordHashClass {
	public String getSHA256(String plainText) {
		String shaString = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256"); //MessageDigest : 암호화하기 위한 클래스
			digest.update(plainText.getBytes()); //Byte를 얻어내 저장
			//getBytes() : encoding/decoding하기위한 메소드
			byte byteData[] = digest.digest();
			StringBuffer buffer = new StringBuffer();
			for(int i = 0; i < byteData.length; i++) { 
				buffer.append(Integer.toString((byteData[i] & 0xff) * 0x100, 16).substring(1));
			}
			shaString = buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			shaString = null;
		}
		
		return shaString;
		
	}
}
```


## 회원가입창 구현 + DB(MySQL)에 삽입 
![create boot](https://raw.githubusercontent.com/mia02125/SpringBoot_Project-Freeboard-/master/pic/pic1.PNG)

### joinService(회원가입 Service)
```java
@Service
public class joinService {
	
	@Autowired
	private UsersRepository usersRepository; //유저값 저장하는 곳
	
	@Autowired
	private UserPasswordHashClass userPasswordHashClass; 
	
	public String joinUser(String userId, String userPw, String userName) { 
		if(userId.equals("") || userPw.equals("") || userName.equals("")) { 
			return "join";
		} else { 
			Users users = new Users();
			users.setUserid(userId);
			String hashPw = userPasswordHashClass.getSHA256(userPw);
			//암호화
			users.setPassword(hashPw);
			users.setUsername(userName);
			usersRepository.save(users);
			return "index";
		}
	}
}
```
### UserController(Login.html과 join.html의 컨트롤러)
```java
public class UsersController {
	
	
	@Autowired  //Service를 자동적으로 사용하게끔함!!!!!
	private joinService joinService; 
	
	@Autowired
	private loginService loginService;
 
	//회원가입 요청받은 값
	@PostMapping("/joinRequest")
	public String joinRequest(@RequestParam Map<String, String> paramMap) {
		String userId = paramMap.get("userid"); // join.html의 name값을 가져옴
		String userPw = paramMap.get("password"); // join.html의 name값을 가져옴
		String userName = paramMap.get("username");// join.html의 name값을 가져옴
		String page = joinService.joinUser(userId, userPw, userName);
		return page;
	}
	
	//로그인 요청받은 값
	@PostMapping("/loginRequest")
	public String loginRequest(@RequestParam Map<String, String> paramMap) {
		String userId = paramMap.get("userid"); //login.html의 name값을 가져옴
		String userPw = paramMap.get("password"); //login.html의 name값을 가져옴
		String page = loginService.login(userId, userPw);
		return page;	
	}
}
```
<br><br><br>

### =========================================================
## 01.08
## 게시판 글쓰기 
![create boot](https://raw.githubusercontent.com/mia02125/SpringBoot_Project-Freeboard-/master/pic/pic3.PNG)

<br><br><br>

### =========================================================
## 01.10
## 게시판 글목록
![create boot](https://raw.githubusercontent.com/mia02125/SpringBoot_Project-Freeboard-/master/pic/pic3_1.PNG)

<br><br><br>

### =========================================================
## 01.11~01.13
## 웹페이지 디자인(CSS, JS) + 게시판 페이지 번호 구현
![create boot](https://raw.githubusercontent.com/mia02125/SpringBoot_Project-Freeboard-/master/pic/read.PNG)
<br>
![create boot](https://raw.githubusercontent.com/mia02125/SpringBoot_Project-Freeboard-/master/pic/write.PNG)

<br><br><br>

### =========================================================
## 01.13
## 게시물 작성일자 구현  +  카카오맵 구현하기(미완성)
![create boot](https://raw.githubusercontent.com/mia02125/SpringBoot_Project-Freeboard-/master/pic/writeTime.PNG)

<br><br><br>

### =========================================================
## 01.15
## 게시물 삭제 구현 + 레이아웃 수정 + JS사용하여 alert이벤트 발생 
#### freeboardController(컨트롤러)
```java
  @RequestMapping(value = "/{freeId}/delete", method = {RequestMethod.GET,RequestMethod.POST}) 
		// GET과 POST를 동시에 받아옴 
		public String getDelete(@PathVariable Long freeId) {
			Freeboard freeboard = freeboardRepository.findByFreeId(freeId); 
			//Repository에서 Id를 받아온다
			freeboardWriteService.delete(freeboard.getFreeId()); //파라미터값 제대로 가져오자!!!
			//freeboardWriteService로 부터 delete메소드를 받고 freeboard생성자의 FreeId값을 가져온다.
			return "redirect:/freeboard"; // freeboard 창으로 이동 
		}
```
### freeboardWriteService

```java
public void delete(Long freeId) {//delete하기 위한 컨트롤러를 위한 서비스 
		freeboardRepository.deleteById(freeId); 
		// freeboardRepository로부터 Id를 지우는 함수를 받는다.
		// freeboardController로 상속 
	}
```

### FreeboardRepository
```java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.model.Freeboard;


@Repository
public interface FreeboardRepository extends JpaRepository<Freeboard, Long>{
	Freeboard findByFreeId(Long freeId);
	Freeboard findByContentAndTitle(String title, String content);
}
```

<br><br><br><br><br><br>

### =========================================================
## 01.17
## 게시물 수정 구현 + 수정일자 구현 + 카카오Map API 구현 
### (초기 작성일자 데이터가 삭제되는 오류 발생...암걸린다.)

### freeboardUpdatePage.html
```html
<div class="container">
			<!-- new ArrayList<Freeboard>()의 boardList의 내용을 board 이라함  -->
			<form th:attr = "action=@{|/${freeboardInfo.freeId}/update|}" method="post">
					
				<div class="form-group">
					<label for="subject"><strong>제목</strong></label> 
					<input style="width: 400px" placeholder="제목" type="text" class="form-control" name="title" />
				</div>
				<div class="form-group">
					<label for="content"><strong>내용</strong></label>
					<textarea style="width: 400px" class="form-control" name="content" rows="3"></textarea>
				</div>			
				<input type="hidden" th:value="${session.loginUser.getUserid()}" name="writer">
				<!-- session.loginUser <= loginService에 있음   -->
				<button type="submit" class="button button5">작성하기</button>
			</form>
</div>
```






### freeboardController

```java
@PostMapping(value = "/{freeId}/update")
		public String modify(ModelMap model, @PathVariable Long freeId , Freeboard freeboard, @RequestParam Map<String, String> paramMap) {
			model.addAttribute("freeboardInfo", freeboardRepository.save(freeboard));
			String updateDate = paramMap.get("updateDate");
			String insertDate = paramMap.get("insertDate");
			freeboardWriteService.update(freeId, updateDate, insertDate);
			return "redirect:/freeboard"; 
			//ModelMap : 데이터만 저장 
			}	
		
```

### freeboardWriteService
```java
public void update(@PathVariable("freeId") Long freeId, String updateDate, String insertDate) {
		try {
			Freeboard freeboard = freeboardRepository.findByFreeId(freeId);
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
			String currentDate = LocalDateTime.now().format(dateFormat); // String값인 현재 시간을 LocalDateTime값으로 변환
			 
			freeboard.setUpdateDate(currentDate);
			freeboard.setInsertDate(insertDate);
			
			freeboardRepository.save(freeboard);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
```

### 카카오맵 구현 
![](https://raw.githubusercontent.com/mia02125/SpringBoot_Project-Freeboard-/master/pic/map.PNG)

<br><br><br>


### =========================================================

## 01.22
### 비밀번호 확인창 구현 + index페이지 Templates 구현
##### -> 그냥 passwordCheck만 추가로 넣어주면 되고 JS로 때려박아서 구현

#### JavaScript
```javascript

<script type="text/javascript">
  	$(function() { 
  		$('#passwordCheck').blur(function() { 
  		if($('#password').val() != $('#passwordCheck').val()) {
  			if($('#passwordCheck').val()!='') {
  				alert("비밀번호가 일치하지않습니다.");
  				$('#passwordCheck').val('');
  				$('#passwordCheck').blur();
  			}
  		}
  	})
});
  </script>

```

#### HTML

```html 

<form action = "/joinRequest" method="post">
    <br>
    <br>
    	<table>
    	<h3><Label>회원가입</Label></h3>
    		<tr>
    			<th>
					<input type = "text" placeholder="아이디" name = "userid" id = "userid"></input><br><br>
					<input type = "password" placeholder="비밀번호"  name = "password" id = "password"></input><br><br>
					<input type = "password" placeholder="비밀번호 확인"  name = "passwordCheck" id = "passwordCheck"></input><br><br>
					<input type = "text" placeholder="이름" name = "username" id = "username"></input><br><br>
					<input type = "submit"  id ="signUpBtn" value = "가입하기"></input>
				<th>
			</tr>
		</table>
	</form>


```

<br><br><br>

### =========================================================
## 01.23
### 각 서비스 오류 제어 (try~catch문) 



<br><br><br>

### =========================================================
## 01.28 
### 카카오맵 현재 위치 확인하기 
```javascript
var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다ㄴ



// HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
if (navigator.geolocation) {
    
    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
    navigator.geolocation.getCurrentPosition(function(position) {
        
        var lat = position.coords.latitude, // 위도
            lon = position.coords.longitude; // 경도
        
        var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
            message = '<div style="padding:5px;">현재 위치</div>'; // 인포윈도우에 표시될 내용입니다
        
        // 마커와 인포윈도우를 표시합니다
        displayMarker(locPosition, message);
            
      });
    
} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
    
    var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),    
        message = 'geolocation을 사용할수 없어요..'
        
    displayMarker(locPosition, message);
}

// 지도에 마커와 인포윈도우를 표시하는 함수입니다
function displayMarker(locPosition, message) {

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({  
        map: map, 
        position: locPosition
    }); 
    
    var iwContent = message, // 인포윈도우에 표시할 내용
        iwRemoveable = true;

    // 인포윈도우를 생성합니다
    var infowindow = new kakao.maps.InfoWindow({
        content : iwContent,
        removable : iwRemoveable
    });
    
    // 인포윈도우를 마커위에 표시합니다 
    infowindow.open(map, marker);
    
    // 지도 중심좌표를 접속위치로 변경합니다
    map.setCenter(locPosition);      
}    


```


<br><br><br>

### =========================================================
