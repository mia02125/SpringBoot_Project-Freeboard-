# SpringBoot(게시판만들기)

### * SpringBoot 초기 설정 
#### ㆍ Lombok(getter & setter 자동 설정) 
#### ㆍ Spring Data JPA 
#### ㆍ Spring Web
#### ㆍ Thymeleaf

### * MVC방식으로 구현
##### ㆍ Route -> Controller -> View로 연결되는 과정 학습
##### ㆍ Route에서 app/HTTP/Controller경로와 사용 function을 지정
##### ㆍ app/HTTP/Controller에서 Function를 만들어 resources/view/보여줄 파일로 전송
##### ㆍ view 폴더에 파일에서 최종적으로 출력

### 01.06
## 가입페이지 로그입 페이지 링크 달기 
![create boot](https://raw.githubusercontent.com/mia02125/SpringBoot_Project-Freeboard-/master/pic/pic.PNG)
## 로그인창 구현 
![create boot](https://raw.githubusercontent.com/mia02125/SpringBoot_Project-Freeboard-/master/pic/pic_1.PNG)
<br>
### 01.07
## 회원가입창 구현 + DB(MySQL)에 삽입 
![create boot](https://raw.githubusercontent.com/mia02125/SpringBoot_Project-Freeboard-/master/pic/pic1.PNG)
## 비밀번호 암호화(SHA-256 알고리즘) 
![create boot](https://raw.githubusercontent.com/mia02125/SpringBoot_Project-Freeboard-/master/pic/pic2.PNG)
<br>
### 01.08
## 게시판 글쓰기 
![create boot](https://raw.githubusercontent.com/mia02125/SpringBoot_Project-Freeboard-/master/pic/pic3.PNG)
<br>
### 01.10
## 게시판 글목록
![create boot](https://raw.githubusercontent.com/mia02125/SpringBoot_Project-Freeboard-/master/pic/pic3_1.PNG)
<br>
### 01.11~01.13
## 웹페이지 디자인(CSS, JS) + 게시판 페이지 번호 구현
![create boot](https://raw.githubusercontent.com/mia02125/SpringBoot_Project-Freeboard-/master/pic/read.PNG)
<br>
![create boot](https://raw.githubusercontent.com/mia02125/SpringBoot_Project-Freeboard-/master/pic/write.PNG)

### 01.13
## 게시물 작성일자 구현   +  카카오맵 구현하기(미완성)
![create boot](https://raw.githubusercontent.com/mia02125/SpringBoot_Project-Freeboard-/master/pic/writeTime.PNG)


### 01.15
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
#### freeboardWriteService

```java
public void delete(Long freeId) {//delete하기 위한 컨트롤러를 위한 서비스 
		freeboardRepository.deleteById(freeId); 
		// freeboardRepository로부터 Id를 지우는 함수를 받는다.
		// freeboardController로 상속 
	}
```
