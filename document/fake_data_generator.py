import csv
import random
import time
from datetime import datetime, timedelta
from faker import Faker
import os

fake = Faker()

# Create output folder
OUTPUT_DIR = "output"
os.makedirs(OUTPUT_DIR, exist_ok=True)

# How many fake rows to generate per minute per category
ROWS_PER_FILE = 1000

# --- File Generators ---

def write_csv(filename, header, rows):
    with open(os.path.join(OUTPUT_DIR, filename), "w", newline="") as file:
        writer = csv.writer(file)
        writer.writerow(header)
        writer.writerows(rows)

def generate_users_file(suffix):
    rows = [[i, fake.name(), fake.email(), random.randint(18, 65)] for i in range(1, ROWS_PER_FILE + 1)]
    write_csv(f"users_{suffix}.csv", ["id", "name", "email", "age"], rows)

def generate_products_file(suffix):
    rows = [[i, fake.word().capitalize(), round(random.uniform(10, 1000), 2), random.randint(1, 10)] for i in range(1, ROWS_PER_FILE + 1)]
    write_csv(f"products_{suffix}.csv", ["id", "name", "price", "category_id"], rows)

def generate_categories_file(suffix):
    rows = [[i, fake.word().capitalize()] for i in range(1, min(ROWS_PER_FILE, 11) + 1)]
    write_csv(f"categories_{suffix}.csv", ["id", "name"], rows)

def generate_orders_file(suffix):
    rows = [
        [i, random.randint(1, 100), random.randint(1, 100), random.randint(1, 5), fake.date_between(start_date='-30d', end_date='today')]
        for i in range(1, ROWS_PER_FILE + 1)
    ]
    write_csv(f"orders_{suffix}.csv", ["id", "user_id", "product_id", "quantity", "order_date"], rows)

def generate_payments_file(suffix):
    rows = [
        [i, random.randint(1, 100), round(random.uniform(20, 1000), 2), random.choice(["credit_card", "paypal", "bank_transfer"])]
        for i in range(1, ROWS_PER_FILE + 1)
    ]
    write_csv(f"payments_{suffix}.csv", ["id", "order_id", "amount", "method"], rows)

def generate_all_categories(suffix):
    generate_users_file(suffix)
    generate_products_file(suffix)
    generate_categories_file(suffix)
    generate_orders_file(suffix)
    generate_payments_file(suffix)

# --- Optional: Generate 10 Past Daily Files ---

def generate_daily_history():
    today = datetime.today()
    for i in range(10, 0, -1):
        date_str = (today - timedelta(days=i)).strftime('%Y%m%d')
        generate_all_categories(date_str)
    print("✅ Generated 10 days of daily data.")

# --- Streaming Loop ---

def run_live_data_stream():
    print("🚀 Starting per-minute data generator...")
    today_str = datetime.now().strftime('%Y%m%d')

    while True:
        now = datetime.now()
        current_suffix = f"{today_str}_{now.strftime('%H%M')}"
        print(f"🕒 Generating data for {current_suffix}")
        generate_all_categories(current_suffix)

        # Sleep until the next full minute
        next_minute = (now + timedelta(minutes=1)).replace(second=0, microsecond=0)
        sleep_seconds = (next_minute - datetime.now()).total_seconds()
        time.sleep(sleep_seconds)

# --- Run ---

if __name__ == "__main__":
    generate_daily_history()     # Optional history generation
    run_live_data_stream()       # Keeps running
