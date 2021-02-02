<template>
   <div>
       <cycleslider style="flex: 1"  @change="sliderChange" :index="selectIndex" :infinite="false">
           <component :ref="'cp'+index1" class="full" :is="item.component" :key="index1" v-for="(item,index1) in items"
                        ></component>
       </cycleslider>
       <div class="tab">
           <tabitem @onChange="change" :key="index1" v-for="(item,index1) in items" :index="index1"
                    :currentIndex="selectIndex" class="tabitem" :text="item.text" :img="item.normalImg"
                    :selectImg="item.selectImg" fontSize="26" color="#313131" selectColor="#1577FF">
           </tabitem>
       </div>
   </div>
</template>

<script>
    export default {
        props: {
            items: {
                default: []
            },
            firstIndex: {
                default: 0
            }
        },
        data() {
            return {
                selectIndex:this.firstIndex,
                loadFlag: {}
            }
        },
        methods: {
            sliderChange(e){
                this.selectIndex=e.index
              // this.log(e)
            },
            change(i){
                this.selectIndex = i;
                if (this.loadFlag[i + ''] == undefined) {
                    this.loadFlag[i + ''] = true
                    let cp = this.$refs['cp' + i][0]
                    if (cp.load) {
                        cp.load()
                    }
                }
                this.$emit('onChange',i)
            },
            onLoad(param) {

            },
            onShow() {

            },
            onHide() {

            },
            onUnload() {

            }
        },
        created() {

        }
    }
</script>

<style scoped>
    .full {
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        margin-top: 0;
        margin-bottom: 0;
    }
    .tab {
        width: 750px;
        height: 120px;
        flex-direction: row;
        background-color: #FEFFFE;
        border-top-width: 1px;
        border-top-color: #dddddd;
        justify-content: center;
    }

    .tabitem {

        flex: 1;
        height: 120px;
    }
</style>
