<template>
    <div style="flex: 1;">
        <div style="flex: 1;position: relative;display: block;">
            <component :ref="'cp'+index1" class="full" :is="item.component" :key="index1" v-for="(item,index1) in items"
                       v-show="index==index1" :style="{visibility:index==index1?'visible':'hidden'}"></component>
        </div>
        <div class="tab">
            <tabitem @onChange="change" :key="index1" v-for="(item,index1) in items" :index="index1"
                     :currentIndex="index" class="tabitem" :text="item.text" :img="item.normalImg"
                     :selectImg="item.selectImg" fontSize="26" color="#313131" selectColor="#1577FF">
            </tabitem>
        </div>
        <div style="height:50px;width:750px;background-color: white " v-if="isFringeScreen" ></div>
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
                index: this.firstIndex,
                loadFlag: {},
                isFringeScreen:weex.requireModule('env')?weex.requireModule('env').isFringeScreen():false
            }
        },
        methods: {

            change(i) {
                this.index = i;
                if (this.loadFlag[i + ''] == undefined) {
                    this.loadFlag[i + ''] = true
                    let cp = this.$refs['cp' + i][0]
                    if (cp.load) {
                        cp.load()
                    }
                }
                // this.$emit('onChange',i)
            },
            onLoad(param) {
                this.loadFlag[this.firstIndex + ''] = true
                let cp = this.$refs['cp' + this.firstIndex][0]
                if (cp.load) {
                    cp.load()
                }
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

    .full {
        position: absolute;
        top: 0;
        bottom: 0;
        right: 0;
        left: 0;
        width: 750px;
    }
</style>