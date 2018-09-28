<template>
    <div style="align-items: center;justify-content: center">
        <image :src="url" ref="img" @click="save" style="width: 200px;height: 200px"></image>
        <text>{{percent}}</text>
        <div @click="downloadandzip" style="width: 100px;height: 100px;background-color: red">

        </div>
    </div>
</template>
<script>
    export default{
        props: {},
        data () {
            return {
                url:'',
                percent:{}
            }
        },
        methods: {
            downloadandzip()
            {

                var net =weex.requireModule('net')
                var file =weex.requireModule('file')
                var url='http://59.110.169.246/img/app.zip'
                net.download(url,(percent)=>{
                    this.percent=percent;
                },(e)=>{
                    this.url=e.path
                    file.unzip(e.path,(res)=>{
                      let px=res.path+'/app'
                        file.ls(px,(fs)=>{
                            this.percent=fs
                        })
                    })
                },()=>{

                })
            },
            ok(){
                var net =weex.requireModule('net')
                var url='https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534089412457&di=1f0c233ef343764f0bc5b277645174cf&imgtype=0&src=http%3A%2F%2Fpic23.photophoto.cn%2F20120503%2F0034034456597026_b.jpg'
                net.download(url,(percent)=>{
                    this.percent=percent;
                },(e)=>{
                    this.url=e.path
                    this.percent=e;
//
                },()=>{

                })
            },
            save()
            {
                var photo =weex.requireModule('photo')
                photo.save(this.url,(res)=>{
                      if(res.success)
                      {
                          this.toast('成功！')
                      }
                    })
            }
        },
        created () {
        }
    }
</script>
<style scoped>
</style>
