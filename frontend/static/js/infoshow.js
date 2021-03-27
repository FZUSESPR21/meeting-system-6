let url = 'http://www.wuzinian.top/backend/infoshow';
var info_show = new Vue({
    el: '.app',
    data: {
        list: [],
    },
    methods: {
        show:function () {
            // alert(this.username+this.password)
            // 传送的数据为json格式
            let data = JSON.stringify();
            /**
             * url 请求路径
             * data 传递数据
             */
            var that = this;
            axios.post(url).then(function (response) {
                // console.log(response);
                // 获取服务端返回的数据
                that.list = response.data;
            }).catch(function (error) {
                console.log(error);
            });
        },
    }
});
