<template>
  <div class="c">
    <text class="t" @click="jump">Profile</text>
  </div>
</template>
<script>
  import router from './index.vue'
  export default {
    methods: {
      jump () {
        router.push('profile')
      }
    }
  }
</script>
<style>
.c{
  align-items:center;
  justify-content:center;
}
.t
{
  color:#000000;
}
.t:active{
  color:#999999;
}
</style>