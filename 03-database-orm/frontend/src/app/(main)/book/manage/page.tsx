'use client'

import { BookData } from '@/app/(main)/page';
import {
  ActionBar, Button, Checkbox,
  CloseButton, Container, Dialog,
  Field, FieldRequiredIndicator, Fieldset,
  Heading, Icon, Input,
  InputGroup, Portal, Progress, RadioGroup,
  Separator, Stack, Table,
  Text
} from '@chakra-ui/react'
import React, { useState } from 'react'
import { FaKey, FaSearch } from "react-icons/fa";
import { LuPlus, LuPencil, LuTrash2, LuSquareDashed, LuRefreshCcw } from "react-icons/lu";

const BookManagePage: React.FC = () => {
  const formatter = new Intl.DateTimeFormat('ko-KR')
  const bookData: BookData[] = [
    { isbn: '9791140710119', title: '스프링 교과서', author: '로렌티우 스필카', date: new Date(2024, 4, 31) },
    { isbn: '9791165922047', title: '스프링으로 시작하는 리액티브 프로그래밍', author: '황정식', date: new Date(2023, 3, 11) },
    { isbn: '9791158395384', title: '스프링 6와 스프링 부트 3로 배우는 모던 API 개발', author: '소라브 샤르마', date: new Date(2024, 11, 5) },
    { isbn: '9791140700448', title: '스프링 마이크로서비스 코딩 공작소', author: '존 카넬, 일러리 후알리루포 산체스', date: new Date(2022, 5, 30) },
    { isbn: '9791191905717', title: '스프링 부트 3 백엔드 개발자 되기: 자바 편', author: '신선영', date: new Date(2024, 3, 5) },
    { isbn: '9788960777330', title: '자바 ORM 표준 JPA 프로그래밍', author: '김영한', date: new Date(2015, 6, 28) },
    { isbn: '9791158395995', title: '스프링 부트 3 핵심 가이드', author: '장정우', date: new Date(2025, 2, 27) },
  ]
  const sortedBookData = bookData.sort((a, b) => +b.date - +a.date)
  const [ selection, setSelection ] = useState<string[]>([])
  const hasSelection = selection.length > 0
  const indeterminate = hasSelection && selection.length < sortedBookData.length
  const [ searchMethod, setSearchMethod ] = useState('title')
  const [ dateMethod, setDateMethod ] = useState<string | null>('all')
  const [ startDate, setStartDate ] = useState<string>('')
  const [ endDate, setEndDate ] = useState<string>('')
  const rows = sortedBookData.map((book) => (
    <Table.Row key={book.isbn} data-selected={selection.includes(book.isbn) ? "" : undefined}>
      <Table.Cell>
        <Checkbox.Root
          size='sm'
          top='0.5'
          checked={selection.includes(book.isbn)}
          onCheckedChange={(changes) => {
            setSelection((prev) =>
              changes.checked
                ? [...prev, book.isbn]
                : selection.filter((isbn) => isbn !== book.isbn)
            )
          }}
        >
          <Checkbox.HiddenInput />
          <Checkbox.Control />
        </Checkbox.Root>
      </Table.Cell>
      <Table.Cell>{book.isbn}</Table.Cell>
      <Table.Cell>{book.title}</Table.Cell>
      <Table.Cell>{book.author}</Table.Cell>
      <Table.Cell>{formatter.format(book.date)}</Table.Cell>
      <Table.Cell>
        <Progress.Root defaultValue={40}>
          <Stack direction='row' gap='4'>
            <Progress.Track flex='1'>
              <Progress.Range />
            </Progress.Track>
            <Progress.ValueText>(1 / 5)</Progress.ValueText>
          </Stack>
        </Progress.Root>
      </Table.Cell>
    </Table.Row>
  ))

  const getPlaceholder = () => {
    switch (searchMethod) {
      case 'isbn':
        return 'ISBN'
      case 'title':
        return '제목'
      case 'author':
        return '저자'
      default:
        return ''
    }
  }

  const handleStartDateChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setStartDate(e.target.value)
    setDateMethod(null)
  }

  const handleEndDateChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setEndDate(e.target.value)
    setDateMethod(null)
  }

  return (
    <Container>
      <Stack>
        <Stack mt='12' gap='6'>
          <Heading as='h1' size='4xl' fontWeight='bold'>도서 관리</Heading>
          <Stack direction='row' justifyContent='space-between' alignItems='end'>
            <Stack direction='row' borderRadius='l3' borderWidth='1px' p='6'>
              <Field.Root>
                <Field.Label>키워드 검색</Field.Label>
                <InputGroup startElement={<FaSearch />}>
                  <Input width='400px' variant='subtle' placeholder={getPlaceholder()}/>
                </InputGroup>
                <RadioGroup.Root pt='2' ps='1' value={searchMethod} onValueChange={(e) => setSearchMethod(e.value!)}>
                  <Stack direction='row' gap='6'>
                    <RadioGroup.Item value='isbn'>
                      <RadioGroup.ItemHiddenInput />
                      <RadioGroup.ItemIndicator />
                      <RadioGroup.ItemText>ISBN</RadioGroup.ItemText>
                    </RadioGroup.Item>
                    <RadioGroup.Item value='title'>
                      <RadioGroup.ItemHiddenInput />
                      <RadioGroup.ItemIndicator />
                      <RadioGroup.ItemText>제목</RadioGroup.ItemText>
                    </RadioGroup.Item>
                    <RadioGroup.Item value='author'>
                      <RadioGroup.ItemHiddenInput />
                      <RadioGroup.ItemIndicator />
                      <RadioGroup.ItemText>저자</RadioGroup.ItemText>
                    </RadioGroup.Item>
                  </Stack>
                </RadioGroup.Root>
              </Field.Root>
              <Separator orientation='vertical' mx='4'/>
              <Field.Root>
                <Field.Label>출판일 검색</Field.Label>
                <Stack direction='row' alignItems='center'>
                  <Input type='date' value={startDate} onChange={handleStartDateChange} max={endDate}/>
                  <Text>~</Text>
                  <Input type='date' value={endDate} onChange={handleEndDateChange} min={startDate}/>
                </Stack>
                <RadioGroup.Root pt='2' ps='1' value={dateMethod} onValueChange={(e) => setDateMethod(e.value)}>
                  <Stack direction='row' gap='6'>
                    <RadioGroup.Item value='all'>
                      <RadioGroup.ItemHiddenInput />
                      <RadioGroup.ItemIndicator />
                      <RadioGroup.ItemText>모두</RadioGroup.ItemText>
                    </RadioGroup.Item>
                    <RadioGroup.Item value='week'>
                      <RadioGroup.ItemHiddenInput />
                      <RadioGroup.ItemIndicator />
                      <RadioGroup.ItemText>최근 1주일</RadioGroup.ItemText>
                    </RadioGroup.Item>
                    <RadioGroup.Item value='month'>
                      <RadioGroup.ItemHiddenInput />
                      <RadioGroup.ItemIndicator />
                      <RadioGroup.ItemText>최근 1달</RadioGroup.ItemText>
                    </RadioGroup.Item>
                  </Stack>
                </RadioGroup.Root>
              </Field.Root>
            </Stack>
            <Stack direction='row' gap='4'>
              <Button variant='outline'><LuRefreshCcw />새로고침</Button>
              <Dialog.Root>
                <Dialog.Trigger asChild>
                  <Button><LuPlus />도서 추가</Button>
                </Dialog.Trigger>
                <Portal>
                  <Dialog.Backdrop />
                  <Dialog.Positioner>
                    <Dialog.Content>
                      <Dialog.Header alignItems='baseline'>
                        <Dialog.Title>도서 추가</Dialog.Title>
                        <Dialog.Description>새로운 도서를 추가합니다</Dialog.Description>
                      </Dialog.Header>
                      <Dialog.Body>
                        <Fieldset.Root>
                          <Fieldset.Content>
                            <Field.Root required>
                              <Field.Label>ISBN <FieldRequiredIndicator /></Field.Label>
                              <Input />
                            </Field.Root>
                            <Field.Root required>
                              <Field.Label>제목 <FieldRequiredIndicator /></Field.Label>
                              <Input />
                            </Field.Root>
                            <Field.Root>
                              <Field.Label>저자</Field.Label>
                              <Input />
                            </Field.Root>
                            <Field.Root>
                              <Field.Label>출판일</Field.Label>
                              <Input type='date'/>
                            </Field.Root>
                          </Fieldset.Content>
                        </Fieldset.Root>
                      </Dialog.Body>
                      <Dialog.Footer>
                        <Dialog.ActionTrigger asChild>
                          <Button variant='outline'>취소</Button>
                        </Dialog.ActionTrigger>
                        <Button>추가</Button>
                      </Dialog.Footer>
                      <Dialog.CloseTrigger asChild>
                        <CloseButton />
                      </Dialog.CloseTrigger>
                    </Dialog.Content>
                  </Dialog.Positioner>
                </Portal>
              </Dialog.Root>
            </Stack>
          </Stack>
          <Text fontSize='sm' color='fg.muted' pl='6'>총 7권 검색됨</Text>
          <Table.Root interactive>
            <Table.ColumnGroup>
              <Table.Column htmlWidth='6'/>
              <Table.Column htmlWidth='15%'/>
              <Table.Column />
              <Table.Column htmlWidth='20%'/>
              <Table.Column htmlWidth='12%'/>
              <Table.Column htmlWidth='13%'/>
            </Table.ColumnGroup>
            <Table.Header>
              <Table.Row>
                <Table.ColumnHeader>
                  <Checkbox.Root
                    size='sm'
                    top='0.5'
                    checked={indeterminate ? 'indeterminate' : selection.length > 0}
                    onCheckedChange={(changes) => {
                      setSelection(changes.checked ? sortedBookData.map((book) => book.isbn) : [])
                    }}
                  >
                    <Checkbox.HiddenInput />
                    <Checkbox.Control />
                  </Checkbox.Root>
                </Table.ColumnHeader>
                <Table.ColumnHeader>
                  <Stack direction='row' alignItems='center'>
                    <Icon color='fg.subtle'><FaKey /></Icon>
                    <Text>ISBN</Text>
                  </Stack>
                </Table.ColumnHeader>
                <Table.ColumnHeader>제목</Table.ColumnHeader>
                <Table.ColumnHeader>저자</Table.ColumnHeader>
                <Table.ColumnHeader>출판일</Table.ColumnHeader>
                <Table.ColumnHeader>상태</Table.ColumnHeader>
              </Table.Row>
            </Table.Header>
            <Table.Body>{rows}</Table.Body>
          </Table.Root>
          <ActionBar.Root open={hasSelection}>
            <Portal>
              <ActionBar.Positioner>
                <ActionBar.Content>
                  <ActionBar.SelectionTrigger>
                    {selection.length} 권 선택됨
                  </ActionBar.SelectionTrigger>
                  <ActionBar.Separator />
                  <Button variant='outline' onClick={() => setSelection([])}><LuSquareDashed />선택해제</Button>
                  <Button variant='outline' disabled={selection.length != 1}><LuPencil />편집</Button>
                  <Button colorPalette='red' variant='surface'><LuTrash2 />삭제</Button>
                </ActionBar.Content>
              </ActionBar.Positioner>
            </Portal>
          </ActionBar.Root>
        </Stack>
      </Stack>
    </Container>
  )
}

export default BookManagePage