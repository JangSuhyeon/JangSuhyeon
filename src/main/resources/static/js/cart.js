// 전체 금액 계산
function calculateTotalAmt() {
    let total = 0;
    $('tr.cart').each(function() {
        const price = $(this).find('input.price-input').val();
        const qty = $(this).find('input.qty-text').val();
        total += price * qty;
    });
    $('#total').text(total.toLocaleString() + "원");
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

    // 결제하기 버튼 클릭 시
    $('#payBtn').click(function () {
        // Todo 장바구니에서 수량이 변경되었을 수도 있으므로..

        // Todo 결제 완료 프로세스
        $.ajax({
            type:'POST',
            url:'/shop/payment',
            success:function () {
                alert("결제가 완료되었습니다!");
                // Todo 주문내역으로
            },
            error:function () {
                alert('결제에 실패했습니다.');
            }
        })

        // Todo 결제 완료 후 결제가 완료되었습니다! alert
    });
})