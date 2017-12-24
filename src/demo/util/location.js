
const data=require('./data.js')

export default {

    get(res)
    {
        var self=this;
        var picker=weex.requireModule("fpicker");
        var d=data.getAddressData();
        var l= d.list
        //务必调用此方法，此方法有初始化的功能，最多3列
        picker.setCount(3);
        picker.setItems1(this.toArray(l))
        picker.setItems2(this.toArray(l[0].children))
        picker.setTheme('#f9f9f9','#4c4c4c')

        var index1=0;x
        var index2=0;
        var index3=0;
        //每个滚轮的change事件
        picker.setChange(function(e){

            index1=e.index;
            picker.setItems2(self.toArray(l[index1].children))
            picker.select(2,0);
            if(l[index1].children.length>0)
            {

                picker.setItems3(self.toArray(l[index1].children[0].children))

                picker.select(3,0);
            }
            else
            {
                picker.setItems3([])
            }

        },function(e){
            index2=e.index;
            if(l[index1].children[index2].children.length>0)
            {
                picker.setItems3(self.toArray(l[index1].children[index2].children))
                picker.select(3,0);
            }
            else
            {
                picker.setItems3([])
            }
        },function(e){
            index3=e.index;
        })
        //点击完成的事件
        picker.setDone(function(e){
            var p1= l[e.index1];
            var p2= l[e.index1].children[e.index2];
            var p3= l[e.index1].children[e.index2].children[e.index3];
            res(p1,p2,p3)

        });
        // picker.show()
        return picker;
    },

    toArray(list)
    {
        var p=[];
        for(let i=0;i<list.length;i++)
        {
            p.push(list[i].name);
        }
        return p;
    },


}


