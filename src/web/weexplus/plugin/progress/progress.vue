<template>
    <div>
        <div id="progress" v-if="show" class="progressRoot">
        </div>
    </div>
</template>
<script>
  export default{
    props: {},
    data () {
      return {
        show:false
      }
    },
    methods: {
      show(){
        this.show=true
      },
      hide(){
        this.show=false
      }
    },
    created () {
    }
  }
</script>
<style scoped>
    .progressRoot{
       position: fixed;
        z-index: 10000;
        top:0;
        left: 0;
        bottom: 0;
        right: 0;
        background-color: rgba(0,0,0,0.3);
    }
</style>
