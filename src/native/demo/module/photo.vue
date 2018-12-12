<template>
    <div>
        <head title="相机/相册"></head>
        <button @click="openAll" text="打开全部"></button>
    </div>
</template>
<script>
  export default{
    props: {},
    data () {
      return {}
    },
    methods: {
      openAll()
      {


        var self=this;
        const photo = weex.requireModule('photo');
        photo.open(500,800,'#000000','#ffffff','#ffffff',function(e){
          self.src=e.path;
          var param={};
          var header={};
          var path={};
          path.file=e.path;
          var net=weex.requireModule("net");
          net.postFile('http://xxx/upload',param,header,path,()=>{
            //start
          },(e)=>{
            //succcess
            var modal=weex.requireModule("modal")
            modal.toast({message:'上传成功！'})
          },()=>{
            //compelete

          },()=>{
            //exception
            var modal=weex.requireModule("modal")
            modal.toast({message:'上传异常！'})
          })
        });


      }
    },
    created () {
    }
  }
</script>
<style scoped>
</style>
