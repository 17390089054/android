
此时很多人会尝试下面的命令把当前分支代码上传到master分支上。

$ git push -u origin master

但依然没能解决问题

$ git push -u origin master

To git@github.com:yangchao0718/cocos2d.git

 ! [rejected]        master -> master (non-fast-forward)

error: failed to push some refs to 'git@github.com:yangchao0718/cocos2d.git

hint: Updates were rejected because the tip of your current branch is behin

hint: its remote counterpart. Integrate the remote changes (e.g.

hint: 'git pull ...') before pushing again.

hint: See the 'Note about fast-forwards' in 'git push --help' for details.



出现错误的主要原因是github中的README.md文件不在本地代码目录中


可以通过如下命令进行代码合并【注：pull=fetch+merge]

git pull --rebase origin master

此时再执行语句 git push -u origin master即可完成代码上传到github



