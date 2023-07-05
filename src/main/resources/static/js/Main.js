/**
 * 
 */
 
var header = $("meta[name='_csrf_header']").attr('content');
var token = $("meta[name='_csrf']").attr('content');

$("#userNickname").ready(function(){
  	$.ajax({
    type : 'GET',           // 타입 (get, post, put 등등)
    url : '/user/username'           // 요청할 서버url
    , beforeSend: function(xhr){
        xhr.setRequestHeader(header, token);
    }
    , success : function(result) { // 결과 성공 콜백함수
        console.log(result);
        console.log(result.username);
        var nicknameTag = $("#userNickname");
        nicknameTag.text(result.username)
    },
    })
})