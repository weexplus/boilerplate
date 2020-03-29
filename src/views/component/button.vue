<template>
    <div>
        <div @click="click" class="btn" :style="{'width':width+'px','height':height+'px'}">
            <text @click="click" class="btn-text">{{text}}</text>
        </div>
    </div>
</template>
<script>
  export default{
    props: {
      text: {
        default: 'button'
      },
      width: {
        default: 200
      },
      height: {
        default: 100
      }
    },
    data () {
      return {}
    },
    methods: {
      click(){
        this.$emit('click')
        this.$emit('onClick')
      }
    },
    created () {
    }
  }
</script>
<style scoped>
    .btn {
        width: 200px;
        height: 100px;
        background-color: #0088fb;
        align-items: center;
        justify-content: center;
        border-radius: 10px;
    }

    .btn:active {
        background-color: #5ac3ff;
    }

    .btn-text {
        color: #ffffff;
        text-align: center;

    }

    .btn-text:active {
        color: #ffffff;
    }
</style>
