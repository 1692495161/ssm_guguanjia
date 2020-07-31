let vm = new Vue({
    el: '.page-content',
    data: function () {
        return {
            pageInfo: {
                pageNum: 1,
                pageSize: 5
            },
            params: {
                status:''
            },
            nodes: [],
            name:'',
            officeName: '全部',
            setting: {  //树配置对象，用于设置树的结构的全局配置
                data: {
                    simpleData: { //简单数据格式设置
                        enable: true,  //开启简单数据格式，自动将一维数组组装成父子结构
                        pIdKey:'parentId'  //默认的父id属性名为pId
                    }
                },
                callback: {  //回调函数设置，用于设置一些事件的回调函数
                    onClick: this.onClick
                },
                view:{
                    fontCss:this.fontCss,
                }
            },
        }
    },
    methods: {
        selectPage: function () {
            axios({
                url: `manager/selectPage/${this.pageInfo.pageNum}/${this.pageInfo.pageSize}`,
                method: 'post',
                data: this.params
            }).then((response) => {
                this.pageInfo = response.data.obj;
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        _selectPage: function (pageNum, pageSize) {
            this.pageInfo.pageNum = pageNum;
            this.pageInfo.pageSize = pageSize;
            this.selectPage();
        },
        selectAll: function () {
            this.params = {
                status:'',
            }
            this.selectPage();
        },
        toDetail:function(id){
            layer.obj=id;

            layer.open({
                type:2,
                title: false,
                area: ['80%', '90%'],
                content: 'manager/admin/detail',
            })
        },
        initTree: function () {
            axios({
                url: 'manager/office/select'
            }).then((response) => {
                this.nodes = response.data.obj;
                //动态添加一个父节点
                this.nodes[this.nodes.length]={id:0,name:'全部机构',open:true}
                /*
                init():初始化树的api
                obj:需要挂载树的jq节点对象
                setting：树的配置对象
                nodes： 树的显示节点信息数组
                */
                // let zTreeObj = $.fn.zTree.init(obj,setting,nodes);
                let zTreeObj = $.fn.zTree.init($('#pullDownTreeone'), this.setting, this.nodes);
            }).catch(error => {
                layer.msg(error.message);
            })
        },
        onClick: function (event, treeId, treeNode) {//点击事件触发的函数
            console.log(event);//事件对象
            console.log(treeId);//树节点id
            console.log(treeNode);//当前点击的节点对象
            //点击获取公司名
            this.officeName=treeNode.name;

            //选中全部就不需要给params.officeId复制，没选中就需要复制
            if(treeNode.id!==0){
                this.params.officeId=treeNode.id;
            }else {
                this.params.officeId='';
            }
        },
        stop:function (event){
            if (event.target.id==="pullDownTreeone_1_switch"){
                event.stopPropagation();
            }
        },
        search:function () {
            //根据树节点获取树对象
            let zTreeObj = $.fn.zTree.getZTreeObj("pullDownTreeone");
            //根据树对象获取父节点，多维数组
            //getNodesByParamFuzzy(key,value,parentNode) 根据父节点parentNode和指定属性名key查找模糊匹配
            let fuzzy = zTreeObj.getNodesByParamFuzzy('name',this.name,null);
            //获取所有的节点，多维结构
            let nodes = zTreeObj.getNodes();
            //转换为一维结构
            let node = zTreeObj.transformToArray(nodes);
            //下一次查询，将所有的父子节点的高亮设为false
            for (let i in node){
                node[i].highLight= false;
                zTreeObj.updateNode(node[i])
            }
            //将模糊查询到的父节点的高亮设为true
            for (let i in fuzzy) {
                fuzzy[i].highLight=true;
                zTreeObj.updateNode(fuzzy[i])
            }
        },
        fontCss: function (treeId,treeNode) {
            return treeNode.highLight?{color:'red'}:{color:'black'};
        }

    },
    created: function () {
        this.selectPage();
    },
    mounted: function () {
        this.initTree();
    }
});