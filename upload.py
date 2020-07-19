# -*- coding: utf-8 -*-
"""
@Time        : 2020/7/19 12:17
@Author      : NingWang
@Email       : yogehaoren@gmail.com
@File        : upload.py
@Description : 
@Version     : 0.1-dev
"""
import sys
import getopt
import os

from utils import Utils


def main(argv):

    username = ""
    password = ""
    cookie_file = ""
    message_file = ""

    use_age = """
    Error: upload.py -u <username> -p <password> -f <upload_message_file_path>
        or: upload.py -c <cookie_path>  -f <upload_message_file_path>
        or: upload.py --username=<username> --password=<password> --message_file=<upload_message_file_path>
        or: upload.py --cookie=<cookie_path> --message_file=<upload_message_file_path>        
            """

    try:
        """
            options, args = getopt.getopt(args, shortopts, longopts=[])

            参数args：一般是sys.argv[1:]。过滤掉sys.argv[0]，它是执行脚本的名字，不算做命令行参数。
            参数shortopts：短格式分析串。例如："hp:i:"，h后面没有冒号，表示后面不带参数；p和i后面带有冒号，表示后面带参数。
            参数longopts：长格式分析串列表。例如：["help", "ip=", "port="]，help后面没有等号，表示后面不带参数；ip和port后面带冒号，表示后面带参数。

            返回值options是以元组为元素的列表，每个元组的形式为：(选项串, 附加参数)，如：('-i', '192.168.0.1')
            返回值args是个列表，其中的元素是那些不含'-'或'--'的参数。
        """
        opts, args = getopt.getopt(argv, "hu:p:f:c:", ["help", "username=", "password=", "message_file=", "cookie="])

    except getopt.GetoptError:
        print(use_age)
        sys.exit(2)

    # 处理 返回值options是以元组为元素的列表。
    for opt, arg in opts:
        if opt in ("-h", "--help"):
            print(use_age)
            sys.exit()
        elif opt in ("-u", "--username"):
            username = arg
        elif opt in ("-p", "--password"):
            password = arg
        elif opt in ("-c", "--cookie"):
            cookie_file = arg
        elif opt in ("-f", "--message_file"):
            message_file = arg

    if cookie_file is not "" and message_file is not "":
        cookie = Utils.load_cookie_from_file(cookie_file)
        upload_message = Utils.load_upload_message_file(message_file)
        Utils.upload_ncov_message(cookie, upload_message=upload_message)
        sys.exit(1)

    if username is not "" and message_file is not "" and password is not "":
        cookie_file = os.path.dirname(os.path.abspath(__file__)) + os.sep + "data" + os.sep + Utils.COOKIE_FILE_NAME
        print("use username and password to upload message, cookie file is save to " + cookie_file)
        Utils.get_cookie_from_login(username, password, cookie_file)
        cookie = Utils.load_cookie_from_file(cookie_file)
        upload_message = Utils.load_upload_message_file(message_file)
        Utils.upload_ncov_message(cookie, upload_message=upload_message)
        sys.exit(1)


if __name__ == "__main__":
    main(sys.argv[1:])
