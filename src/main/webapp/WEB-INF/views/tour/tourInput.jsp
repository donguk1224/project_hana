<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<style>
div.vbox {
	display: flex;
	flex-flow: column;
	justify-content: center;
	align-items: center;
}
div.hbox {
	display: flex;
	flex-flow: row;
	justify-content: center;
	align-items: center;
}
.w1200 {
	width: 1200px;
}
.h500 {
	min-height: 500px;
}
div.sb {
	justify-content: space-between;
}
div.fs {
	justify-content: flex-start;
}
div.fe {
	justify-content: flex-end;
}
div.boda {
	border: 1px solid #dadada;
	padding: 10px;
	width: 1180px;
	margin: 5px;
}
input[type="text"],
input[type="password"], 
select, 
textarea {
	all: unset;
	padding: 5px;
	border-bottom: 2px solid black;
}
textarea .content {
	width: 1000px;
	height: 200px;
}
select {
	border: 2px solid black;
}
.btn {
	background-color: #dadada;
	color: black;
	font-weight: bold;
	cursor: pointer;
	padding: 10px;
	border: 0;
}
.btn:hover {
	background-color: black;
	color: white;
}
h2 {
	text-align: center;
}
label {
	margin: 5px;
}
</style>

<main>
	<div class="vbox w1200 write">
	<form method="POST">
		<input type="hidden" name="member_idx" placeholder="작성자를 입력하세요" value="${login.idx }">
		<div class="w1200 fs">
			<p><input type="text" name="name" placeholder="제목을 입력하세요" autofocus required></p>
			<div class="w1200 h500 boda">
				<div><textarea style="width: 1000px; height: 200px;" name="content" placeholder="기본정보를 작성하세요" required></textarea></div>
				<div><textarea style="width: 1000px; height: 200px;" name="how_to" placeholder="사용방법을 작성하세요" required></textarea></div>
				<div><textarea style="width: 1000px; height: 200px;" name="matter" placeholder="유의사항을 작성하세요" required></textarea></div>
				<div><textarea style="width: 1000px; height: 200px;" name="refund_rule" placeholder="취소 및 환불규정을 작성하세요" required></textarea></div>
			</div>
			<p><input type="file" name="head_img">대표 이미지</p>
			<p><input type="file" name="con_img">기본 정보 이미지</p>
		</div>
			<tr>
                <td>*</td>
                <td>쿠폰 사용 여부</td>
                <td>미사용<input type="radio" name="coupon_yn" value="0" required/>&nbsp;&nbsp;
                	사용<input type="radio" name="coupon_yn" value="1" required/>
                </td>
            </tr>
            <tr>
                <td>*</td>
                <td>예약 가능 시점</td>
                <td><input id="usable" type="text" name="usable" required/></td>
            </tr>
            <tr>
                <td>*</td>
                <td>해쉬태그</td>
                <td><input id="hashtag" type="text" name="hashtag" required/></td>
            </tr>
		<div class="w1200 sb hbox">
			<div>
				<input type="button" value="돌아가기" class="btn" onclick="history.go(-1)">
			</div>
			<div>
				<input type="submit" value="작성" class="btn">
			</div>
		</div>
	</form>
	</div>
</main>

<script>
	const cpath = '${pageContext.request.contextPath}';
	const uploadForm = document.getElementById('uploadForm');
	const file = document.querySelector('input[type="file"]');
	const list = document.getElementById('list');
	
	const getFileList = function() {
		const url = cpath + '/imgList';
		const opt = {
			method: 'GET'
		}
		fetch(url, opt)
		.then(resp => resp.json())
		.then(json => {
			console.log(json);
			json.forEach(element => {
				const img = document.createElement('img');
				img.src = cpath + '/tourInput/' + element;
				img.style.height = '200px';
				list.appendChild(img)
			});
		});
	}
	getFileList();
		
	const uploadHandler = function(event) {
		event.preventDefault();
		const formData = new FormData(uploadForm);
		
		const url = cpath + '/tourInput';
		const opt = {
			method: 'POST',
			body: formData
		};
		
		fetch(url, opt)
		.then(resp => resp.text())
		.then(text => {
			console.log(text);
			const img = document.createElement('img');
			img.src = cpath + text;
			img.style.height = '200px';
			list.appendChild(img)
		});
		
	}
	uploadForm.onsubmit = uploadHandler;
</script>

<%@ include file="../layout/footer.jsp" %>