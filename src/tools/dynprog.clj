(ns
  ^{:author raptor_MVK}
  tools.dynprog)

(declare parts-by-coll-count sums-count)

(defn parts-by-coll-count
  "Given N and collection, returns the number of partitions of N by elements of the
  collection"
  [n coll]
  (loop [cur-coll (take-while #(<= % n) coll)
         res (concat [1] (repeat n 0))]
    (if (empty? cur-coll)
      (last res)
      (recur (rest cur-coll)
        (let [cur (first cur-coll)]
          (loop [i cur
                 cur-res res]
            (if (> i n)
              cur-res
              (recur (inc i) (update-in (vec cur-res) [i]
                               #(+' % (nth cur-res (- i cur))))))))))))

(defn sums-count
  "Given N and collection, returns the number of ways to represent N as sum of at least
  two elements of the collection"
  [n coll]
  (loop [cur-coll (take-while #(<= % n) coll)
         res (concat [1] (repeat n 0))]
    (if (empty? cur-coll)
      (last res)
      (recur (rest cur-coll)
        (let [start (first cur-coll)]
          (loop [i start
                 new-res res]
            (if (> i n)
              new-res
              (recur (inc i) (update-in (vec new-res) [i]
                               #(+' % (nth new-res (- i start))))))))))))
