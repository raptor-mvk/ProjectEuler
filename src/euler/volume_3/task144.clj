; Topic: geometry

(ns
  ^{:author raptor_MVK}
  euler.volume_3.task144
  (:use clojure.test)
  (:use tools.geom)
  (:use tools.math))

(defn reflects-count
  "Given start and first hit points, returns the number of times laser beam hit the
  internal surface of the white cell (ellipse with the equation 4*x^2+y^2=100) before
  exiting through the section [-0.01, 0.01] on the top of the white cell"
  [start hit]
  (let [next-hit (fn [start [x y :as hit]]
                   (let [reflect-line (point+normal2line hit [y (- (* 4.0 x))])
                         next-point (reflect start reflect-line)
                         [a b c :as next-line] (points2line hit next-point)
                         next-hit-xs (solve-sqr-eq [(+ (* a a) (* 4.0 b b)) (* 2.0 a c)
                                                    (- (* c c) (* 100.0 b b))])
                         next-hit-x (first (filter #(> (Math/abs (- % x)) 1e-8)
                                             next-hit-xs))
                         next-hit-y (Math/sqrt (- 100.0 (* 4.0 (sqr next-hit-x))))]
                     (if (< (Math/abs (distance [next-hit-x next-hit-y] next-line)) 1e-8)
                       [next-hit-x next-hit-y]
                       [next-hit-x (- next-hit-y)])))]
    (loop [begin start
           [x y :as end] hit
           i 1]
      (if (and (> x -0.010001) (< x 0.010001) (> y 0.0))
        (dec i)
        (recur end (next-hit begin end) (inc i))))))

(deftest test1 (is (= (reflects-count [0 10.1] [1.4 -9.6]) 354)))

(time (run-tests 'euler.volume_3.task144))
