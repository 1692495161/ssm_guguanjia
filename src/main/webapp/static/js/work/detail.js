let vm=new Vue({
    el:'.page-content',
    data:{
        workOrder:{}
    },
    methods:{
        selectDetail:function (id) {
            axios({
                url:'manager/admin/selectDetail',
                params:{
                    id:id
                }
            }).then((response)=>{
                this.workOrder=response.data.obj;
            }).catch(error=>{
                layer.msg(error.message)
            })
        }
    },
    created:function () {
        this.selectDetail(parent.layer.obj)
    }
});