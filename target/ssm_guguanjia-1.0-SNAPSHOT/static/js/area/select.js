let vm = new Vue({
    el: '.page-content',
    data: function () {
        return {
            nodes: [],
            setting: {  //树配置对象，用于设置树的结构的全局配置
                data: {
                    simpleData: { //简单数据格式设置
                        enable: true,  //开启简单数据格式，自动将一维数组组装成父子结构
                        pIdKey: 'parentId'  //默认的父id属性名为pId
                    }
                },
                //回调函数设置，用于设置一些事件回调函数
                callback: {
                    onClick: this.onClick,
                },
            },
            name: '',
        }
    },
    methods: {
        initTree: function () {
            axios({
                url: 'manager/area/select'
            }).then((response) => {
                this.nodes = response.data.obj;
                //动态添加一个父节点
                this.nodes[this.nodes.length] = {id: 0, name: '全部', open: true}
                /*
                init():初始化树的api
                obj:需要挂载树的jq节点对象
                setting：树的配置对象
                nodes： 树的显示节点信息数组
                */
                // let zTreeObj = $.fn.zTree.init(obj,setting,nodes);
                let zTreeObj = $.fn.zTree.init($('#select-tree'), this.setting, this.nodes);
            }).catch(error => {
                layer.msg(error.message);
            })
        },
        onClick: function (event, treeId, treeNode) {//点击事件触发的函数
            /*console.log(event);//事件对象
            console.log(treeId);//树节点id
            console.log(treeNode);//当前点击的节点对象*/

            console.log(treeNode);
            parent.layer.parentName = treeNode.name;
            parent.layer.parentId = treeNode.id;
            //父区域的parentIds+父区域id+','
            parent.layer.parentIds = treeNode.parentIds + treeNode.id + ',';
            let index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
            /*this.pId = treeNode.id;
            this.selectPage();*/

        },
        search: function () {
            //根据树节点获取树对象
            let zTreeObj = $.fn.zTree.getZTreeObj("select-tree");
            //根据树对象模糊查询到树节点
            let fuzzy = zTreeObj.getNodesByParamFuzzy('name', this.name, null);
            // console.log(fuzzy);
            this.nodes = fuzzy;
            this.initTree2();
        },
        initTree2: function () {
            if (this.name != '') {
                //动态添加一个父节点
                this.nodes[this.nodes.length] = {id: 0, name: '全部'}
                /*
                init():初始化树的api
                obj:需要挂载树的jq节点对象
                setting：树的配置对象
                nodes： 树的显示节点信息数组
                */
                // let zTreeObj = $.fn.zTree.init(obj,setting,nodes);
                let zTreeObj = $.fn.zTree.init($('#select-tree'), this.setting, this.nodes);
            } else {
                this.initTree();
            }
        }
    },
    created: function () {
        // this.selectPage();
    },
    mounted: function () {
        this.initTree();
    }
});