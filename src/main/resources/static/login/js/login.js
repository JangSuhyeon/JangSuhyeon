$(function () {

    // 로그인 버튼 클릭 시
    $('#login-btn').click(function () {
        console.log('click');
        $.ajax({
            type:'POST',
            url:'/login',
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify({
                account: $('#account').val(),
                password: $('#password').val()
            }),
            success:function (res) {
                alert('로그인 성공!');
            },
            error: function (xhr) {
                var errorResponse = xhr.responseJSON; // 서버에서 반환한 JSON 에러 응답
                alert('로그인 실패: ' + errorResponse.message);
            }
        })
    });
})