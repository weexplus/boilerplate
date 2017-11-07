echo off

set nginx_home=F:\njix\nginx-1.13.4
set workspace_home=%CD%
cd %nginx_home%

tasklist | findstr /i "nginx.exe"

if %ERRORLEVEL% == 0 (
    goto :stop_nginx
    goto :start_nginx
    goto :eof
) else (
    goto :start_nginx
    goto :eof
)

:stop_nginx
    echo "nginx is running, stopping..."
    rem nginx -s stop
    TASKKILL /F /IM nginx.exe /T
    echo "stop ok"
    pause

:start_nginx
    echo "nginx is not running, starting"
    start "" %nginx_home%\\nginx.exe  -c %nginx_home%\\conf\\nginx.conf
    echo "start ok"
    cd %workspace_home%
    npm run dev
    pause
    
 echo off

