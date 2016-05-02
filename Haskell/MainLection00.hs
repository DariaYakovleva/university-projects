{-#LANGUAGE UnicodeSyntax, TypeOperators, NoImplicitPrelude #-}

module MainLection00 where

import Prelude
--import Level0

f x = x +1
test = f (-1)

g x = x + 1.0

h x y = x +y
h' = \x y -> x +y

test'' = h 1
-- test'' = \y -> 1 +y
test2' = \y -> 1 +y

foo x = x
test3' = foo


--(\x -> (\y -> x +y))
--(\y -> 1 +y)
test3 = (\x -> x)

--(\ x -> B)  ---> B [x:= y ]
--(\ x -> x +1) 2
-- ---> (x +1) [x:= 2 ]
-- ---> (2 +1)

--(\x -> \y -> x +y) 1 2

--if

-- test5 = if 1 == 1 then 1 else 2

test5 x = if 1 == 1
    then x +2
    else 2


test5 x = if x > 0
    then 1
    else if x == 0
        then 0
        else (-1)




kelv x = x + 273.15
celc x = x - 273.15
comp x = celc(kelv x) == x


otherwise' = True
signum' x | x < 0 = -1
         | x == 0 = 0
         |otherwise' = 1

data MaybeBool = True' | False' | Whatever
    deriving Show
smaller ::  Int -> Int -> MaybeBool
smaller x y | x < y  = True'
            | x > y = False'
            | otherwise = Whatever


data Ord' = Less | Equal | More
    deriving Show

showOrd' :: Ord' -> String
showOrd' x = case x of
    Less -> "Less"
    Equal -> "Equal"
    More -> "More"

compare' x y | x == y = Equal
         | x < y = Less
         | x > y = More

--showOrd' :: Ord' -> String
--showOrd'' Less = "Less"
--showOrd'' Equal = "Equal"
--showOrd'' More = "More"

eqOrd' :: Ord' -> Ord' -> Bool
eqOrd' Less Less = True
eqOrd' More More = True
eqOrd' Equal Equal = True
eqOrd' x y = False
eqOrd' _ _ = False -- notnamed named argument
