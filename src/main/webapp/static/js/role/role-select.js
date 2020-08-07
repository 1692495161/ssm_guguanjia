let vm =new Vue({
    el:'.page-content',
    data:function () {
        return{
            setting:{
                data:{
                    simpleData:{
                        enable:true,
                        pIdKey:'parentId'
                    }
                },
                callback:{
                    onClick:this.onClick
                }
            },
            nodes:[],
        }
    },
    methods:{
        initTree:function () {
            axios({
                url:'manager/office/select',
            }).then(response=>{
                this.nodes=response.data.obj;
                this.nodes[this.nodes.length]={
                    id:'0',
                    name:'全部机构',
                    open:true
                }

                $.fn.zTree.init($('#select-tree'),this.setting,this.nodes)
            }).catch(error=>{
                layer.msg(error.message)
            })
        },
        onClick:function (event, treeId, treeNode) {
            parent.layer.parentName=treeNode.name;
            parent.layer.parentId=treeNode.id;

            let index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    },
    mounted:function () {
        this.initTree();
    }
});