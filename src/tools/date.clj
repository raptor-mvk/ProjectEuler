(ns
  ^{:author "raptor_MVK"}
  tools.date)

(declare days-in-month leap-year?)

(defn days-in-month
  "Given month and year, returns number of days in this month, months are numbered from
   zero"
  [month year]
  (cond
    (= month 1) (if (leap-year? year) 29 28)
    (some #{month} #{3 5 8 10}) 30
    :else 31))

(defn leap-year?
  "Given N, returns true, if N-th year is leap, and false otherwise"
  [n]
  (or (= 0 (rem n 400)) (and (= 0 (rem n 4)) (> (rem n 100) 0))))