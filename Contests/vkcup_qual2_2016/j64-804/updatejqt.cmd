@rem update (or install) the qtide binaries
@rem run from the J install directory
@cd /d %~dp0
@bin\jconsole.exe -js "exit install'qtide'"
@pause
