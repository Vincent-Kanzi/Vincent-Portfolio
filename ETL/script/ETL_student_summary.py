import pandas as pd 
import pymysql
from urllib.parse import quote_plus
from sqlalchemy import create_engine

host = 'xxxxxxxxxx'
user = 'root'
password = '*********'
port = xxxx


def connect(db_name):
    return pymysql.connect(host=host, user=user, password=password, database=db_name, port=port)


#EXTRACT
soso = pd.read_sql("SELECT * FROM student_records", connect('soso'))
sopi = pd.read_sql("SELECT * FROM operations_log", connect('sopi'))
ad  = pd.read_sql("SELECT * FROM user_accounts", connect('ad'))
sopa = pd.read_sql("SELECT * FROM payment_history", connect('sopa'))
fin = pd.read_sql("SELECT * FROM fee_balances", connect('finance'))
pcb = pd.read_sql("SELECT * FROM printing_logs", connect('pcb'))

#TRANSFORM
sopi_summary = sopi.sort_values('action_date').groupby('student_id').last().reset_index()
sopa_summary = sopa.sort_values('payment_date').groupby('student_id').last().reset_index()
pcb_summary = pcb.groupby('user_id').agg(total_pages_printed=('pages_printed', 'sum'), last_print_date=('print_date', 'max')).reset_index().rename(columns={'user_id': 'student_id'})

#COPY OF SOS
df = soso.copy()

#MERGE 
df = df.merge(ad.rename(columns={'user_id': 'student_id'}), on='student_id', how= 'left')
df = df.merge(fin, on='student_id', how='left')
df = df.merge(sopa_summary[['student_id', 'amount', 'payment_date']], on= 'student_id', how='left')
df = df.merge(pcb_summary, on='student_id', how='left')
df = df.merge(sopi_summary[['student_id', 'action', 'action_date']], on='student_id', how='left')

df.rename(columns={
    'amount': 'last_payment_amount',
    'payment_date': 'last_payment_date',
    'action': 'last_action',
    'action_date' : 'last_action_date'
    }, inplace=True)

encoded_password = quote_plus(password)
engine = create_engine(f'mysql+pymysql://{user}:{encoded_password}@{host}:{port}/data_warehouse')

# print("\nPreview of final DataFrame to load into warehouse:")
# print(df)

df.to_sql('student_summary', con=engine, if_exists='replace', index=False)
# print(f"\n {len(df)} rows inserted into data_warehouse.student_summary")
print("\nETL completed successfully, Data is loaded into data_warehouse. under table(student_summary)")
