# 媒体中心 (MediaSwitcher)

一个极简的 Android 启动器,桌面上只有一个图标,点开后可以切换打开:

- 📺 **番剧** → Kazumi
- 📚 **漫画** → Venera
- 📖 **拷贝漫画** → EasyCopy

**注意**:这只是一个"快捷方式启动器",三个 app 需要你事先安装在手机上。它不包含任何番剧/漫画内容,只是帮你把三个 app 收在一个入口里。如果点某个 app 提示未安装,会尝试跳转到下载页。

## 如何下载 APK

本仓库配好了 GitHub Actions,push 到 main 分支会自动编译 APK。

1. 在 GitHub 仓库页面点顶部 **Actions** 标签
2. 等「Build MediaSwitcher APK」编译完成(约 3-5 分钟)
3. 点进完成的 run,在底部 **Artifacts** 区域下载 `media-switcher-apk`
4. 解压得到 `app-debug.apk`,传到手机上安装

## 如何使用

1. 先安装好三个 app(如果你要用的话):
   - Kazumi: https://github.com/Predidit/Kazumi/releases
   - Venera: https://github.com/venera-app/venera/releases
   - EasyCopy: 搜索下载
2. 安装本 APK
3. 桌面上会出现「媒体中心」图标,点它打开选择界面
4. 点任意卡片打开对应 app

## 添加更多 app

编辑 `app/src/main/java/com/tliming/mediaswitcher/MainActivity.kt`,在 `apps` 列表里加一个 `AppEntry` 即可。需要同时在 `AndroidManifest.xml` 的 `<queries>` 里加上包名。

## 许可证

MIT
