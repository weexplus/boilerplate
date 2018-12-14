<template>
    <div>
        <head title="相机/相册"></head>
        <button @click="time" text="打开全部"></button>

        <image :src="src" style="width: 500px;height: 700px"></image>
    </div>
</template>
<script>
  export default{
    props: {},
    data () {
      return {
        src:''
      }
    },
    methods: {

      showMonthPicker(param,callback){
        var date = new Date()
        var year = date.getFullYear()
        var mon = date.getMonth() + 1
        let start=param.start
        let end=param.end
        let bgcolor=param.bgcolor
        let btncolor=param.btncolor
        if(start==undefined){
          start=1970
        }
        if(end==undefined){
          end=year
        }

        if(bgcolor==undefined){
          bgcolor='#000000'
        }
        if(btncolor==undefined){
          btncolor='#ffffff'
        }

        let p=weex.requireModule('fpicker')
        p.setCount(2)
        p.setTheme( bgcolor, btncolor)
        let years=[]
        let month=[]
        for(let i=end;i>=start;i--){
          years.push(i+'')
        }
        for(let i=1;i<=12;i++){
          month.push(i+'')
        }
        p.setItems1(years)
        p.setItems2(month)
        p.select(1,years.indexOf(year+''))
        p.select(2,month.indexOf(mon+''))
        p.setDone((res)=>{
          let m=month[res.index2]
          if(m<10){
            m='0'+m
          }
           callback(years[res.index1]+'-'+m)
        })
        p.show()

      },
      time(){
        this.showMonthPicker({},(res)=>{
           this.alert(res)
        })
      },
      openAll()
      {


        var self=this;
        const photo = weex.requireModule('photo');
        photo.open(500,800,'#000000','#ffffff','#ffffff',function(e){
          self.src=e.path;

        });


      }
    },
    created () {
    }
  }
</script>
<style scoped>
</style>
