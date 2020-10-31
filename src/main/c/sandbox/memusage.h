#ifndef MEMUSAGE_H
#define MEMUSAGE_H

#include <sys/types.h>

int memusage(pid_t pid);
void memusage_init(void);
void memusage_close(void);

#endif
