let url = 'http://www.wuzinian.top/backend/Login';

var log_in = new Vue({
    el: '#form1',
    data: {
        list: [],
        username: '',
        password:'',
    },
    methods: {
        login:function () {
            // alert(this.username+this.password)
            // 传送的数据为json格式
            let data = JSON.stringify({
                username: this.username,
                password: this.password
            });
            /**
             * url 请求路径
             * data 传递数据
             */
            var that = this;
            axios.post(url, data).then(function (response) {
                // console.log(response);
                // 获取服务端返回的数据
                vm.$data.list = response.data;
                //判断逻辑
                if(data.result=='success'){
                    window.location.href="static/html/index.html";
                }else{
                    alert("账号或密码错误");
                    window.location.href="static/html/login.html";
                }
            }).catch(function (error) {
                console.log(error);
            });
        },
        func:function () {
            window.location.href="signup.html";
        }
    }
});