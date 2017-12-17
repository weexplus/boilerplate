@echo off
xcopy  dist\*   platforms\android\vshop\app\src\main\assets\app /y /s /e
xcopy  dist\*   platforms\ios\vshop\app /y /s /e
xcopy  src\img\*   dis\img /y /s /e
xcopy  src\config.json   platforms\android\vshop\app\src\main\assets\app\config.json /y /s /e
xcopy  src\config.json   platforms\ios\vshop\app\config.json /y /s /e
pause



