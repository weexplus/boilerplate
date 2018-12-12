<template>
    <div>
        <head title="相机/相册"></head>
        <button @click="openAll" text="打开全部"></button>

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
