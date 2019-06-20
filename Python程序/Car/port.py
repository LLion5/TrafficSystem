import pymysql
import time

def port_to_data(car_id,station_id):
    # 打开数据库连接
    db = pymysql.connect ("localhost", "root", "123456", "lightdata")

    # 使用cursor()方法获取操作游标
    cursor = db.cursor ()

    # SQL 插入语句
    s = time.strftime ('%Y.%m.%d.%H.%M.%S', time.localtime (time.time ()))

    sql = "INSERT INTO car" + "(date, car_id, station_id)"\
        + "VALUES" + "('" + s + "'," + car_id + "," + station_id + ");"
    try:
        # 执行sql语句
        cursor.execute (sql)
        # 提交到数据库执行
        db.commit ()
    except:
        # 如果发生错误则回滚
        db.rollback ()

    # 关闭数据库连接
    db.close ()

