abstract class List[A+] {

def first(): List[A] = head
def rest(): List[A] = tail

def firstn(n: Int): List[A] =
  if (n == 0 || isEmpty) Nil 
  else first() :: rest().firstn(n - 1)

def restn(n: Int): List[A] =
  if (n == 0 || isEmpty) this 
  else rest().restn(n - 1)
}


def fe(a):
	atom(a): 0
	true: first(first(a))
def fc(a):
	atom(a): a
	true : rest(first(a))
def atom(a):
	a is not List: true
	true:	false
|a|:
	atom(a): 0
	true: 1 + |rest(a)|
#a:
	atom(a): 1
	true: #fe(a) + #rest(a)
a@b:
	atom(a): b
	true: first(a) . rest(a)@b
