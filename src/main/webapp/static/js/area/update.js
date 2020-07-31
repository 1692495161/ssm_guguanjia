let vm = new Vue({
    el: '.page-content',
    data: {
        area: {}
    },
    methods: {
        toUpdate:function(){
            axios({
                url:'manager/area/toUpdate',
                method:'put',
                data: this.area
            }).then((response)=>{
                parent.layer.success=response.data.success;
                let index = parent.layer.getFrameIndex(window.name);
                if (response.data.success){
                    parent.layer.close(index);
                    parent.layer.msg(response.data.msg);
                }else{
                    layer.msg(response.data.msg);//子窗口中提示
                }
            }).catch((error)=>{
                layer.msg(error.message)
            })
        },
        toModules:function () {
            layer.open({
                type: 2,
                title: false,
                area: ['100%', '100%'],
                content: 'manager/area/toModules',
                end:() => {
                    if (layer.icon!=undefined&&layer.icon!=''){
                        this.area.icon=layer.icon;
                    }
                },
            });
        },
        toSelect:function () {
            layer.open({
                type: 2,
                title: false,
                area: ['100%', '100%'],
                content: 'manager/area/toSelect',
                end:() => {
                    if (layer.parentName!=undefined&&layer.parentName!=''){
                        this.area.parentName=layer.parentName;
                        this.area.parentId=layer.parentId;
                        this.area.parentIds=layer.parentIds;
                    }
                },
            });
        },
    },
    created: function (){
        this.area=parent.layer.obj;
        this.area.oldParentIds=this.area.parentIds;
    }
});