let vm = new Vue({
    el: '.page-content',
    data: {
        pageInfo: {
            //初始化  第一次页面加载的时候使用
            pageNum: 1,
            pageSize: 5

        },
        app:{},
        active:true,
    },
    methods: {
        selectPage: function () {

            axios({
                url: `manager/app/selectPage/${this.pageInfo.pageNum}/${this.pageInfo.pageSize}`,
            }).then((response) => {
                /*console.log(response);*/
                this.pageInfo = response.data.obj;

            }).catch((error) => {
                console.log(error);
            })
        },

        _selectPage: function (pageNum, pageSize) {
            this.pageInfo.pageNum = pageNum;
            this.pageInfo.pageSize = pageSize;
            this.selectPage();
        },

        toUpdate: function (app) {
            //借助当前窗口的layer对象传递值到子窗口
            layer.obj = app;
            layer.open({
                type: 2,
                area: ['700px', '550px'],
                fixed: false, //不固定
                content: 'manager/app/toUpdate',
                end: () => { //关闭子窗口后的回调函数
                    if (layer.success == undefined || !layer.success) {
                        this.selectPage();
                    }
                }
            });
        },

        doDelete: function (id) {
            layer.msg('确认删除！！', {
                time: 0 //不自动关闭
                , btn: ['是', '否']
                , yes: (index) => {  //点击第一个成功按钮的回调函数
                    layer.close(index);  //关闭当前窗口

                    // layer.msg("模拟发送ajax请求，删除");

                    let app={id: id, delFlag: 1}
                    axios({
                        url: 'manager/app/doUpdate',
                        method: 'put',
                        data: app
                    }).then((response) => {
                        if (response.data.success) {
                            this.selectPage();
                            layer.msg("删除成功");
                        }
                    }).catch((error) => {
                        layer.msg(error.message);
                    })
                }
            })
        },

        changeActive:function () {
            this.active=!this.active;
        },
        save:function () {

            axios({
                url:'manager/app/doInsert',
                method:'post',
                //将数据传入后台
                data:this.app,
            }).then((response)=>{
                layer.msg(response.data.msg);
                this.changeActive();
                this.selectPage();
                //添加成功之后清空
                this.app='';
            }).catch((error)=>{
                layer.msg(error.message);
            })
        }
    },
    created: function () {
        this.selectPage();
    }
})