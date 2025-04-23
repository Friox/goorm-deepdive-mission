'use client'

import { Button, Text, CloseButton, Container, Dialog, Field, FieldRequiredIndicator, Fieldset, Heading, Input, InputGroup, Portal, RadioGroup, Separator, Stack, SimpleGrid, Card } from '@chakra-ui/react'
import React, { useState } from 'react'
import { FaSearch } from 'react-icons/fa'
import { LuRefreshCcw, LuPlus } from 'react-icons/lu'
import { BookData } from '../../page'
import { LuBook } from "react-icons/lu";

const BookListPage: React.FC = () => {

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

  const [ searchMethod, setSearchMethod ] = useState('title')

  const [ startDate, setStartDate ] = useState<string>('')
  const [ endDate, setEndDate ] = useState<string>('')

  const handleStartDateChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setStartDate(e.target.value)
    setDateMethod(null)
  }
    
  const handleEndDateChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setEndDate(e.target.value)
    setDateMethod(null)   
  }

  const [ dateMethod, setDateMethod ] = useState<string | null>('all')

  const bookData: BookData[] = [
    { isbn: '9791140710119', title: '스프링 교과서', author: '로렌티우 스필카', date: new Date(2024, 4, 31) },
    { isbn: '9791165922047', title: '스프링으로 시작하는 리액티브 프로그래밍', author: '황정식', date: new Date(2023, 3, 11) },
    { isbn: '9791158395384', title: '스프링 6와 스프링 부트 3로 배우는 모던 API 개발', author: '소라브 샤르마', date: new Date(2024, 11, 5) },
    { isbn: '9791140700448', title: '스프링 마이크로서비스 코딩 공작소', author: '존 카넬, 일러리 후알리루포 산체스', date: new Date(2022, 5, 30) },
    { isbn: '9791191905717', title: '스프링 부트 3 백엔드 개발자 되기: 자바 편', author: '신선영', date: new Date(2024, 3, 5) },
    { isbn: '9788960777330', title: '자바 ORM 표준 JPA 프로그래밍', author: '김영한', date: new Date(2015, 6, 28) },
    { isbn: '9791158395995', title: '스프링 부트 3 핵심 가이드', author: '장정우', date: new Date(2025, 2, 27) },
  ]

  return (
    <Container>
      <Stack>
        <Stack mt='12' gap='6'>
          <Heading as='h1' size='4xl' fontWeight='bold'>도서 목록</Heading>
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
            </Stack>
          </Stack>
          <SimpleGrid columns={4} gap='6'>
            {bookData.map((book, idx) => (
              <Card.Root key={idx}>
                <Card.Header>
                  <Card.Title textOverflow='ellipsis' textWrap='nowrap' maxLines='1' overflow='hidden'>{book.title}</Card.Title>
                  <Card.Description>{book.author}</Card.Description>
                </Card.Header>
                <Card.Body>
                  <Stack gap='3'>
                    <Stack direction='row' justifyContent='space-between'>
                      <Text color='fg.muted' fontSize='sm'>{book.date.toISOString().split('T')[0]} 출판</Text>
                      <Text color='fg.muted' fontSize='sm'>대출 : 5 / 5</Text>
                    </Stack>
                    <Button variant='surface' size='sm'><LuBook />대출</Button>
                  </Stack>
                </Card.Body>
              </Card.Root>
            ))}
          </SimpleGrid>
        </Stack>
      </Stack>
    </Container>
  )
}

export default BookListPage