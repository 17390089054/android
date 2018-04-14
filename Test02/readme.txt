Android Project项目详解
	src:资源文件 存放可编写的JAVA文件
	gen:系统自动生成的文件(不可更改) 包含R文件  R文件包含各个内部类 分别指向res目录下的
	各个配置文件(如 string --> res/values/string.xml)
	R.java将保存所有的资源Id。（关于资源id命名需注意资源文件只能以小写字母和下划线做首字母，
	随后的名字中只能出现 [a-z0-9_.] 这些字符）
	android5.0:当前系统版本所需要的jar开发包 
	Android Private Libraries:个人开发的功能拓展包
	asset:可以存放项目一些较大的资源文件
		与drawable文件夹一样也是存放资源的文件夹，不同的是，放在assets文件下的资源，并不会在R文件中生成id，
		这些文件可以不被压缩打包至apk。例如：音乐、数据库文件等。
	bin:编译目录 运行后生成的apk
	lib:第三方jar包
	res:
		drawble:存放图片资源(应用程序图标) dpi(像素) 由小到大:l-m-h-xh-xxh
		layout:应用程序主要布局文件 包含各个资源文件 
			存放所有的布局文件，主要是用于排列不同的显示组件，在Android程序中要读取此XML文件来实现布局的效果。layout文件夹也有类似drawable文件夹一样，也可以实现不同分辨率的布局、甚至设备横竖屏的不同布局。
			如：layout-land：存放横屏布局文件；layout-prot：存放竖屏布局文件
		menu:菜单配置文件
		values:包含尺寸 字符串 样式配置文件
		values-sw600dp/values-sw720dp-land: 不同安卓版本下应用程序的尺寸替换文件
		values-v11/values-v14:不同安卓版本下样式替换文件
	AndroidMainfest.xml:清单文件（系统自动生成） 安卓主要管理界面 （很重要）  包名 版本名  最低SDK 目标SDK 应用配置文件
	活动配置文件  intent-filter过滤不同的活动
	你千万不要去改变他的名称和文件类型，你只能去编辑文件里的内容。
	清单文件相当于app一个身份标识，包含了当前APP的版本、名称、所有具有的权限、
	运行APP的硬件需求、Android系统的特性（如多点触控）、拥有的Activity、服务、广播、内容提供器等
	
	
	proguard-project.txt:代码混淆文件
	project.properties：项目配置文件 可更改版本号
		
	
		