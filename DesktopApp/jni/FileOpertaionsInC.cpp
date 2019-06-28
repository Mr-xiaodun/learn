#include "team_fileoperations_FileOperationsImp.h"
#include <windows.h>
#include <stdio.h>

JNIEXPORT jstring JNICALL Java_team_fileoperations_FileOperationsImp_findURootDirCImp
(JNIEnv *env, jobject thisObject) {
	//用windws sdk 查找磁盘信息，找出u盘盘符，并且返回 
	UINT diskType;
	LPWSTR drivesLetters=new WCHAR[256];
	char buffer[256];
	jstring drivePathsInStr=NULL;
	int drivesNum,Ucount=0;
	drivesNum = GetLogicalDriveStringsW(256, drivesLetters);
	drivesNum = drivesNum / 4; //GetLogicalDriveStrings返回字符串总长度，每四个宽字符表示一个盘符，所以要除以4
	for (int idx = 0; idx < drivesNum; idx++,drivesLetters+=4) {
		diskType = GetDriveTypeW(drivesLetters);
		if (diskType == DRIVE_REMOVABLE) {
			//获取所有的u盘符
			buffer[Ucount++] = drivesLetters[0];
		}
	}
	buffer[Ucount] = '\0';
	printf("found %d U roots\n", Ucount);
	if(Ucount!=0){
		drivePathsInStr = env->NewStringUTF(buffer);
	}
	return drivePathsInStr;
}

JNIEXPORT jstring JNICALL Java_team_fileoperations_FileOperationsImp_findBackupDirCImp
(JNIEnv *env, jobject thisObject) {
	return NULL;
}