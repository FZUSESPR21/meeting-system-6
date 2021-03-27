let url = 'http://localhost:8080/MyShop_war_exploded/User';
var login = new Vue({
    el: '#app6',
    data: {
        list: [],
        id: '',
        password:"",
        methods: {
            login() {
                // 传送的数据为json格式
                let data = JSON.stringify({
                    id: this.id,
                    password: this.password
                });
                /**
                 * url 请求路径
                 * data 传递数据
                 */
                axios.post(url, data).then(function (response) {
                    // console.log(response);
                    // 获取服务端返回的数据
                    vm.$data.list = response.data;
                    //判断逻辑
                    if(true){
                        window.location.href="static/html/study.html";
                    }else{
                        //....
                    }
                }).catch(function (error) {
                        console.log(error);
                    });
            }
        }
    }
});