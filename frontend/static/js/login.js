let url = 'http://localhost:8080/MyShop_war_exploded/User';

var log_in = new Vue({
    el: '#form1',
    data: {
        list: [],
        username: '',
        password:'',
    },
    methods: {
        login:function () {
            //alert(this.username+this.password)
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
                // if(true){
                //     window.location.href="static/html/study.html";
                // }else{
                //     //....
                // }
            }).catch(function (error) {
                console.log(error);
            });
        },
        func:function () {
            window.location.href="signup.html";
        }


    }
});