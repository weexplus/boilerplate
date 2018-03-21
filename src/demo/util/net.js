
var host='http://59.110.169.246/movie/'
// var host='http://192.168.1.101:8080/'


const net ={

    postShort:  function (weg,param,header,start,success,compelete)
    {
        var modal=weex.requireModule("modal")
        this.postFull(weg,param,header,start,success,function(res){
            //fail
            modal.toast({message:res.msg})
        },function(){
            //exception
            modal.toast({message:'网络异常！'})
        },function(){
            //compelete

            compelete();
        })

    },

    postFull:  function (weg,param,header,start,success,fail,exception,compelete)
    {
        var net=weex.requireModule("net")
        var modal=weex.requireModule("modal")
         var self=this;
        var url=host+weg;
        var st = weex.requireModule('static') ;
        var token=st.getString('token')
        if(token!=undefined&&token!='')
        {
            header.token=token
        }
       // param.token='95d594d7b18fd1c7db37e81dd5bae9c9'
        net.post(url,param,header,function(){
            //start
            start();
        },function(e){
            //success
            // modal.toast({message:e.res.err})
            if(e.res.err==0)
            {

                success(e.res)
            }
            else
            {
                // modal.toast({message:e.res.msg})
                if(token!=undefined&&token!='')
                {
                    st.remove('token')
                    return;
                }
                if(e.res.err==1000)
                {
                    // var nav=weex.requireModule("navigator")
                    // nav.presentFull('root:busi/account/login.js',{},'transparent',true,function(){
                    //     self.postFull(weg,param,header,start,success,fail,exception,compelete);

                    // },true);
                }
                else
                fail(e.res);
            }

        },function(e){
            //compelete


            compelete();

        },function(e){
            // exception
            exception();
        });

    },

    post:function (weg,param,success)
    {
        var progress=weex.requireModule("progress")
        this.postShort(weg,param,{},function(){
            progress.show();
        },success,function(){
            progress.dismiss();
        });
    },

    postSilent:function (weg,param,success)
    {

        this.postFull(weg,param,{},function(){

        },success,function(res){
            //fail

        },function(){
            //exception

        },function(){
            //compelete


        })
    }

}
export default net

