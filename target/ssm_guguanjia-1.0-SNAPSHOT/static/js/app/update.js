let vm =new Vue({
    el:'.page-content',
    data:{
        app:{},
        success:'',
    },
    methods:{
        doUpdate:function () {
            axios({
                url:'manager/app/doUpdate',
                method:'put',
                data: this.app
            }).then((response)=>{  //剪头函数  自动传递上下文的this
                //成功后关闭当前子窗口
                //注意：parent 是 JS 自带的全局对象，可用于操作父页面

                //获取子窗口的索引
                let index = parent.layer.getFrameIndex(window.name);
                //将更新信息传入父窗口
                this.success = response.data.success;
                parent.layer.success=this.success;
                if (response.data.success){
                    //更新成功，关闭子窗口，在父窗口提示
                    parent.layer.close(index);
                    parent.layer.msg(response.data.msg);
                }else {
                    layer.msg(response.data.msg)
                }
            }).catch((error)=>{
                //异常信息
                layer.msg(error.message)
            })
        },
        cancel:function () {
            //获取子窗口的索引
            let index = parent.layer.getFrameIndex(window.name);
            if (this.success==undefined || !this.success){
                parent.layer.close(index);
            }
        }
    },
    created:function () {
        //获取父窗口parent的layer对象
        this.app=parent.layer.obj;
    }
});