let url = 'http://www.wuzinian.top/backend/User';

var select = new Vue({
    el: '#select',
    data: {
        forumname:'',
        selected:'',
        list: [],
    },
    methods: {
        select:function () {
            var that = this;
            axios.post(url).then(function (response) {
                that.list = response.data;
            }).catch(function (error) {
                console.log(error);
            });
        },
        changeSelect:function (index) {
            var that = this;
            let data = JSON.stringify({
                forumname: list[index].forumname,
                selected: !list[index].selected
            });
            axios.post(url,data).then(function (response) {
                // console.log(response);
                // 获取服务端返回的数据
                that.list = response.data;
                //判断逻辑
                if(that.list=='success'){
                    window.location.href="static/html/index.html";
                }else{
                    alert("满员了");

                }
            }).catch(function (error) {
                console.log(error);
            });
        },
    }
});

var info = new Vue({
    el: '#info',
    data: {
        list: [],
    },
    methods: {
        info:function () {
            var that = this;
            axios.post(url).then(function (response) {
                that.list = response.data;
            }).catch(function (error) {
                console.log(error);
            });
        },
    }
});
