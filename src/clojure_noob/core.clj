(ns clojure-noob.core
  (:gen-class))

(defn x-chop
  ([name chop-type]
   (str "I " chop-type " chop " name "! Take hat"))
  ([name]
   (x-chop name "karate")))

(defn weird-arity
  ([]
    "tons of stuff")
  ([number]
   (inc number))
  )

(weird-arity 2)

(defn codger-communication
  [whippersnapper]
  (str "Get off my lawn, " whippersnapper)
  )

(codger-communication "yea")

(defn codger
  [& whippersnappers]
  (map codger-communication whippersnappers)
  )

(codger "Bill" "Swaggers" "Yea")

(defn favorite-things
  [name & things]
  (str "Hi, " name ", here are my favorite things: "
       (clojure.string/join ", " things)
       )
  )

(favorite-things "John" "JS" "Clojure" "Node")

(defn my-first
  [[first-thing]]
  first-thing)

(my-first ["oven" "bike" "waraxe"])

(defn chooser
  [[first-choice second-choice & unimportant-choices]]
  )

(defn announce-location
  [{lat :lat lng :lng}]
  (println (str "Lat: " lat))
  (println (str "Lng: " lng))
  )

(defn receive-location
  [{:keys [lat lng] :as location}]
  (println (str "Lat: " lat))
  (println (str "Lng: " lng))
  (println (str "Map: " location))
  )

(announce-location {:lat 1 :lng 2})
(receive-location {:lat 1 :lng 3})

(defn yolo [& args]
  (println (str "1"))

  )

(defn illustrate
  []
  (+ 1 304)
  30
  "joe")

(illustrate)

(defn number-comment
  [x]
  (if (> x 6)
    "That's big yo"
    "Tiny num"))

(number-comment 2)

(map (fn [name] (str "Hi, " name))
     ["Darth Vader" "Mr. Magoo"])

(map #(str "Hi, " %1)
     ["Darth Vader" "Mr. Magoo"])

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn needs-matching-part?
  [part]
  (println part)
  (re-find #"^left-" (:name part)))

(needs-matching-part? (get asym-hobbit-body-parts 1))
(needs-matching-part? {:name "left-"})


(defn make-matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(make-matching-part {:name "left-swagger" :size 10})

(defn symetrize-body-parts
  "Expects a seq of maps which have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if(empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts
            final-body-parts (conj final-body-parts part)]
        (if (needs-matching-part? part)
          (recur remaining (conj final-body-parts (make-matching-part part)))
          (recur remaining final-body-parts))))))

(symetrize-body-parts asym-hobbit-body-parts)

(loop [i 0
       final 10]
  (println (str "Iteration N" i))
  (if (> i 10)
    (println "IM DONE YO")
    (let [final (+ final i)]
      (recur (inc i) final))))

(reduce + [1 2 3 4])

(defn reduced-symmetrize
  "Uses reduce to implmenet symmetrize"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (let [final-body-parts (conj final-body-parts part)]
              (if (needs-matching-part? part)
                (conj final-body-parts (make-matching-part part))
                final-body-parts)))
          []
          asym-body-parts))

(reduced-symmetrize asym-hobbit-body-parts)

(defn hit
  [asym-body-parts]
  (let [sym-parts (reduced-symmetrize asym-body-parts)
        ; This is using map to select the size property from the sym-parts map.
        ; Kind of like pluck from lodash.
        ; Then it reduces over the vector created from the map
        body-part-size-sum (reduce + 0(map :size sym-parts))
        target (inc (rand body-part-size-sum))]
    (loop [[part & rest] sym-parts
           accumulated-size (:size part)]
      (if (> accumulated-size target)
        part
        (recur rest (+ accumulated-size (:size part)))))))

(hit asym-hobbit-body-parts)


























