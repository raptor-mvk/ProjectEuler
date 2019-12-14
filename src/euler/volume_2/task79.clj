; Topic: strings
; Idea: full search on the sorted list of sequences

(ns
  ^{:author "raptor_MVK"}
  euler.volume_2.task79
  (:use clojure.test)
  (:use tools.comb)
  (:use tools.conversions))

(defn min-passcode
  "Given a sequence of 3 digit numbers, returns the shortest possible number of unknown
  length such, that each element of sequence is its three digits taken in order"
  [coll]
  (let [sorted-coll (sort (distinct coll))]
    (loop [cur-coll (map num2seq (rest sorted-coll))
           res [(num2seq (first sorted-coll))]]
      (if (empty? cur-coll)
        (seq2num (first res))
        (let [upd-left (fn upd-left [head tail-res tail-cur]
                         (if (empty? tail-cur)
                           (vector (concat head tail-res))
                           (let [cur (first tail-cur)
                                 cur-pos (.indexOf tail-res cur)]
                             (if (= -1 cur-pos)
                               (map #(concat head %) (mix-perms tail-res tail-cur))
                               (upd-left (concat head (take (inc cur-pos) tail-res))
                                 (drop (inc cur-pos) tail-res) (rest tail-cur))))))
              upd-right (fn upd-right [tail head-res head-cur]
                          (if (empty? head-cur)
                            (vector (concat head-res tail))
                            (let [cur (last head-cur)
                                  cur-pos (.indexOf (reverse head-res) cur)]
                              (if (= -1 cur-pos)
                                (map #(concat % tail) (mix-perms head-res head-cur))
                                (upd-right (concat (take-last (inc cur-pos) head-res)
                                             tail) (drop-last (inc cur-pos) head-res)
                                  (butlast head-cur))))))
              upd-middle (fn [res cur]
                           (if-not (= -1 (.indexOf res (second cur)))
                             (vector (concat [(first cur)] res [(last cur)]))
                             []))
              cur (first cur-coll)
              new-res (map #(concat (upd-left [] % cur) (upd-right [] % cur)
                              (upd-middle % cur)) res)]
          (recur (rest cur-coll)
            (distinct (last (first (sort #(compare (first %1) (first %2))
                                     (group-by count (reduce into new-res))))))))))))

(def arr1 [319 680 180 690 129 620 762 689 762 318 368 710 720 710 629 168 160 689 716
           731 736 729 316 729 729 710 769 290 719 680 318 389 162 289 162 718 729 319
           790 680 890 362 319 760 316 729 380 319 728 716])

(deftest test1 (is (= (min-passcode arr1) 73162890)))

(time (run-tests 'euler.volume_2.task79))
