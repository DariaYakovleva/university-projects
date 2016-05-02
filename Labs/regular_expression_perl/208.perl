while (<>) {
	s/(\d+)(0)(\D)/\1\3/g;
	print;
}