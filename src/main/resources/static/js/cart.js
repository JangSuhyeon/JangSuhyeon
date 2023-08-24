// 전체 금액 계산
function calculateTotalAmt() {
    let total = 0;
    $('tr.cart').each(function() {
        const price = $(this).find('input.price-input').val();
        const qty = $(this).find('input.qty-text').val();
        let subTotal = price * qty;
        total += subTotal;
        $(this).find('.tr-sub-total').text(subTotal.toLocaleString() + "원");
    });
    $('#total').text(total.toLocaleString() + "원");
}

// 포트원 결제
var IMP = window.IMP;    // 아임포트 JavaScript 라이브러리
IMP.init('imp49336905'); // Todo 변수로 변환 필요 ,IMP 객체를 초기화
function payment() {

    // 주문번호 생성을 위함
    var today = new Date();
    var hours = today.getHours(); // 시
    var minutes = today.getMinutes();  // 분
    var seconds = today.getSeconds();  // 초
    var milliseconds = today.getMilliseconds();
    var makeMerchantUid = hours +  minutes + seconds + milliseconds;

    // 결제창 호출
    IMP.request_pay(
        {
            pg: "kcp.A52CY", // Todo 변수로 변환 필요
            pay_method: "card",
            merchant_uid: "SHOP" + makeMerchantUid, // 주문번호
            name: "SHOP 가구 구매",                   // 상품명
            amount: 1004,                           // 결제금액
            buyer_email: "Iamport@chai.finance",    // 구매자 이메일
            buyer_name: "포트원 기술지원팀",           // Todo 구매자 이름 로그인 정보로 수정 필요
            buyer_tel: "010-1234-5678",             // 구매자 번호
            buyer_addr: "서울특별시 강남구 삼성동",     // 구매자 주소
            buyer_postcode: "123-456",              // 구매자 번호
        },
        function (rsp) {
            if (rsp.success) {
                // orders 객체에 상품 정보 추가
                var orders = {};
                $("tr.cart").each(function() {
                    var prtId = $(this).find('input.price-input').val();
                    var qty = $(this).find('input.qty-text').val();
                    orders[prtId] = qty;
                });

                // 주문내역 저장
                $.ajax({
                    type:'POST',
                    url:'/shop/payment',
                    dataType:'appliction/json',
                    data:JSON.stringify(orders),
                    success:function () {
                        alert("결제가 완료되었습니다!");
                        // 주문내역으로 이동
                        window.location.href = "/shop/order";
                    },
                    error:function () {
                        console.log("주문내역 저장에 실패했습니다.");
                    }
                })
            } else {
                alert('결제에 실패했습니다.');
            }
        }
    );
}

$(function () {
    calculateTotalAmt();

    // 장바구니 추가 버튼 클릭 시
    $('#addToCartBtn').click(function () {
        $.ajax({
            type:'POST',
            url:'/shop/addToCart',
            data:$('#addToCartFrm').serialize(),
            success:function () {
                const response = confirm("장바구니에 추가하였습니다. 장바구니로 이동하시겠습니까?");
                if (response) {
                    // 장바구니로 이동
                    window.location.href = "/shop/cart";
                }
            },
            error:function () {
                console.log("장바구니 추가를 실패했습니다.");
            }
        })
    });
})