
$("#myForm").submit(function () {

    var tips;
    if($("#headname").val()!=''){
        tips="名称："+$("#headname").val()+"<br/>";
    }
    if($("#taxno").val()!=''){
        tips+="纳税人识别号："+$("#taxno").val()+"<br/>";
    }
    if($("#mobile").val()!=''){
        tips+="手机："+$("#mobile").val()+"<br/>";
    }
    if($("#email").val()!=''){
        tips+="邮箱："+$("#email").val()+"<br/>";
    }
    if($("#tel").val()!=''){
        tips+="固话："+$("#tel").val()+"<br/>";
    }
    if($("#address").val()!=''){
        tips+="地址："+$("#address").val()+"<br/>";
    }
    if($("#bankname").val()!=''){
        tips+="开户行："+$("#bankname").val()+"<br/>";
    }
    if($("#accountno").val()!=''){
        tips+="银行账号："+$("#accountno").val()+"<br/>";
    }


    bootbox.confirm({
        title:'开票信息',
        message:tips,
        buttons:{
            confirm:{
                label: '确定',
                className: 'btn-success'
            },
            cancel:{
                label: '取消',
                className: 'btn-danger'
            }
        },

        callback: function (result) {
            if(result){

                $.ajax({
                    type: "POST",
                    url: "/invoice",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    data: $('#myForm').serialize(),
                    dataType: "text",
                    async: true,
                    beforeSend: function () {
                        $.blockUI({message: '<image src="/images/loading.gif"></image><br>' + '请稍等……'});
                    },
                    complete: function () {
                        $.unblockUI();
                    },
                    success: function (response) {
                        //console.log(response.data)//浏览器中打印服务端返回的数据(调试用)
                        obj = eval("(" + response + ")");
                        $("#message").html(obj.data);
                        $("#alert_like").modal("show");
                        document.body.innerHTML = "";
                    },
                    error: function () {
                        //$("#alert_like").modal("show")
                        alert("异常！");
                    }
                })


            }
        }
    });


})

