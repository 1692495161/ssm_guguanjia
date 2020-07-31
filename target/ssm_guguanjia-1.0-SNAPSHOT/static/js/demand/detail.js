let vm=new Vue({
    el:'.page-content',
    data:{
        demand:{}
    },
    methods: {

    },
    created:function () {
        this.demand=parent.layer.obj;
    }
});