(ns movies.core
  (:gen-class))

(defn zeros
  [n]
  (vec (repeat n 0)))

(defn zeros
  [n]
  (if (zero? n) []
      (conj (zeros (- n 1)) 0)))

(defn zeros
  [n]
  (loop [result [] n n]
    (if (zero? n) result
        (recur (conj result 0) (- n 1)))))

(defn zeros
  [n]
  (nth (iterate #(conj % 0) []) n))

(zeros 7)

(defn -main
  []
  (println "Hello world!"))

(mapv (fn [v] (* 2 v)) (vec (range 1 9)))
;;=> [2 4 6 8 10 12 14 16]

(reduce-kv (fn [r k v] (conj r [k v])) [] (vec (range 1 9)))
;;=> [[0 1] [1 2] [2 3] [3 4] [4 5] [5 6] [6 7] [7 8]]

(defn fizzbuzzer
  [n]
  (let [fb (fn [d s] (if (zero? (mod n d)) s ""))
        s (str (fb 3 "Fizz") (fb 5 "Buzz"))]
    (if (empty? s) n s)))

(map fizzbuzzer (range 1 100))

(defn x+1 [x]  (+ x 1))
(defn x*3 [x] (* x 3))

(macroexpand '(x*3 (-> 5 x+1))) ;;=> (x*3 (-> 5 x+1))

(macroexpand '(-> 5 (fn [x] (+ x 1)) x*3)) ;;=> (x*3 (fn 5 [x] (+ x 1)))
