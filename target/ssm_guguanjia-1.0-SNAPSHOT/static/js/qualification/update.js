    let vm = new Vue({
    el: '.page-content',
    data: {
        qualification: {}
    },
    methods:{
        doUpdate:function (check) {
            this.qualification.check=check;
            //不更新地址
            this.qualification.address=null;
            axios({
                url:'manager/qualification/doUpdate',
                method:'put',
                data: this.qualification
            }).then((response)=>{
                let index=parent.layer.getFrameIndex(window.name);
                parent.layer.success=response.data.success;
                if (response.data.success){
                    //更新成功后，关闭子窗口，父窗口提示
                    parent.layer.close(index);
                    parent.layer.msg(response.data.msg)
                }else {
                    layer.msg(response.data.msg);
                }
            }).catch((error)=>{
                layer.msg(error.message)
            })
        }
    },
    created:function () {
        this.qualification=parent.layer.obj;
    }
});